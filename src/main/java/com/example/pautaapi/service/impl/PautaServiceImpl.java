package com.example.pautaapi.service.impl;

import java.util.Optional;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.domain.Voto;
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
        return Optional.of(this.getPauta(idPauta))
            .map(pauta -> pauta.abrirSecao(minutos))
            .map(repository::save)
            .get();
    }

    @Override
    public Pauta adicionarVoto(String idPauta, Voto voto) {
        return Optional.of(this.getPauta(idPauta))
            .map(pauta -> pauta.adicionarVoto(voto))
            .map(repository::save)
            .get();
    }
}
