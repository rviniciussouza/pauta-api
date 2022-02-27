package com.example.pautaapi.service;

import static org.mockito.Mockito.mock;

import com.example.pautaapi.configs.CpfApiConfig;
import com.example.pautaapi.exception.CpfInvalidoException;
import com.example.pautaapi.rest.response.CpfResponse;
import com.example.pautaapi.service.impl.CpfServiceImpl;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

public class CpfServiceTest {
    
    private final String CPF = "CPF";
    private CpfResponse cpfValido = new CpfResponse("ABLE_TO_VOTE");
    private CpfResponse cpfInvalido = new CpfResponse("UNABLE_TO_VOTE");

    private RestTemplate restTemplate;
    private CpfApiConfig cpfApiConfig;
    private CpfServiceImpl cpfService;

    @BeforeEach
    public void setUp() {
        this.cpfApiConfig = new CpfApiConfig();
        this.cpfApiConfig.setUrl("url");
        this.restTemplate = mock(RestTemplate.class);
        this.cpfService = new CpfServiceImpl(restTemplate, cpfApiConfig);
    }

    @Test
    @DisplayName("Deve validar o cpf com sucesso")
    public void requestCpfValido() {
        Mockito.when(restTemplate.getForObject(String.format(cpfApiConfig.getUrl(), CPF), CpfResponse.class)).thenReturn(cpfValido);
        Throwable exception = Assertions.catchThrowable(
            () -> cpfService.validar(this.CPF));
        assertThat(exception).isNull();
    }

    @Test
    @DisplayName("Deve obter uma excessão CpfInvalidoException ")
    public void requestCpfInvalido() {
        Mockito.when(restTemplate.getForObject(String.format(cpfApiConfig.getUrl(), CPF), CpfResponse.class)).thenReturn(cpfInvalido);
        
        Throwable exception = Assertions.catchThrowable(
            () -> cpfService.validar(this.CPF));
        assertThat(exception).isInstanceOf(CpfInvalidoException.class);
    }
}
