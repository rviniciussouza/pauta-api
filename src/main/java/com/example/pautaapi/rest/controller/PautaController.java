package com.example.pautaapi.rest.controller;

import com.example.pautaapi.service.PautaService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pautas")
public class PautaController {
    @Autowired
    private PautaService service;

    @Autowired
    private ModelMapper modelMapper;
}
