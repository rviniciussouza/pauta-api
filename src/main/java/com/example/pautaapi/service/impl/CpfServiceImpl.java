package com.example.pautaapi.service.impl;

import com.example.pautaapi.configs.CpfApiConfig;
import com.example.pautaapi.exception.CpfInvalidoException;
import com.example.pautaapi.rest.response.CpfResponse;
import com.example.pautaapi.service.CpfService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CpfServiceImpl implements CpfService {
  
    private final Logger logger = LoggerFactory.getLogger(CpfService.class);

    RestTemplate restTemplate;

    CpfApiConfig cpfApiConfig;

    @Autowired
    public CpfServiceImpl(RestTemplate restTemplate, CpfApiConfig cpfApiConfig) {
        this.restTemplate = restTemplate;
        this.cpfApiConfig = cpfApiConfig;
    }

    @Override
    public void validar(String cpf) {
        logger.info("Validando CPF {}", cpf);
        CpfResponse response = request(cpf);
        this.apto(cpf, response);
    }
    
    private CpfResponse request(String cpf) {
        try {
            return restTemplate.getForObject(String.format(cpfApiConfig.getUrl(), cpf), CpfResponse.class);
        }
        catch(RestClientException ex) {
            logger.error(ex.getMessage(), cpf);
            throw new CpfInvalidoException(cpf);
        }
    }

    private void apto(String cpf, CpfResponse response) {
        if(!response.getStatus().equals("ABLE_TO_VOTE")) {
            throw new CpfInvalidoException(cpf);
        }
    }
}
