package com.example.pautaapi.service.impl;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.repository.PautaRepository;
import com.example.pautaapi.service.PautaService;

import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {
    private PautaRepository repository;

    public PautaServiceImpl(PautaRepository repository) {
        this.repository = repository;
    }

    public Pauta criarPauta(Pauta pauta) {
        return repository.save(pauta);
    }

}