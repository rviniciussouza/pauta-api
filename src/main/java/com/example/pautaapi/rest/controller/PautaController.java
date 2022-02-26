package com.example.pautaapi.rest.controller;

import javax.validation.Valid;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.rest.request.PautaRequest;
import com.example.pautaapi.rest.response.PautaResponse;
import com.example.pautaapi.service.PautaService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pautas")
public class PautaController {
    @Autowired
    private PautaService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaResponse criarPauta(@RequestBody @Valid PautaRequest pautaRequest) {
        Pauta pauta = modelMapper.map(pautaRequest, Pauta.class);
        return modelMapper.map(service.criarPauta(pauta), PautaResponse.class);
    }
}