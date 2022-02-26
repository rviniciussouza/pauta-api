package com.example.pautaapi.service.impl;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.exception.PautaNaoEncontradaException;
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

    public Pauta getPauta(String idPauta) {
        return repository.findById(idPauta)
            .orElseThrow(
                () -> new PautaNaoEncontradaException(idPauta));
    }

    public Pauta abrirSessao(String idPauta, Integer minutos) {
        return repository.findById(idPauta)
        .map(pauta -> pauta.abrirSecao(minutos))
        .map(repository::save)
        .orElseThrow(() -> new PautaNaoEncontradaException(idPauta));
    }
}
