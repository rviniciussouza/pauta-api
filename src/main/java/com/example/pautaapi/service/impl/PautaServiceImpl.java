package com.example.pautaapi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.pautaapi.constants.OpcaoVoto;
import com.example.pautaapi.constants.ResultadoVotacao;
import com.example.pautaapi.constants.StatusPauta;
import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.domain.Voto;
import com.example.pautaapi.exception.PautaNaoEncontradaException;
import com.example.pautaapi.repository.PautaRepository;
import com.example.pautaapi.rest.response.ResultadoResponse;
import com.example.pautaapi.service.CpfService;
import com.example.pautaapi.service.PautaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {

    private PautaRepository repository;
    private CpfService cpfService;

    @Autowired
    public PautaServiceImpl(PautaRepository repository, CpfService cpfService) {
        this.repository = repository;
        this.cpfService = cpfService;
    }

    public Pauta salvar(Pauta pauta) {
        return repository.save(pauta);
    }

    public Pauta getPauta(String idPauta) {
        return repository.findById(idPauta)
                .orElseThrow(
                        () -> new PautaNaoEncontradaException(idPauta));
    }

    public Pauta abrirSessao(String idPauta, Integer minutos) {
        return Optional.of(this.getPauta(idPauta))
                .map(pauta -> {
                    pauta.setStatus(StatusPauta.ABERTA);
                    return pauta.abrirSessao(minutos);
                }).map(repository::save)
                .get();
    }

    public Pauta encerrarSessao(String idPauta) {
        return Optional.of(this.getPauta(idPauta))
            .map(pauta -> {
                pauta.setStatus(StatusPauta.ENCERRADA);
                return pauta;
            })
            .map(repository::save)
            .get();
    }


    @Override
    public Pauta adicionarVoto(String idPauta, Voto voto) {
        cpfService.validar(voto.getCpfAssociado());
        return Optional.of(this.getPauta(idPauta))
                .map(pauta -> pauta.adicionarVoto(voto))
                .map(repository::save)
                .get();
    }

    @Override
    public ResultadoResponse obterResultadoVotacao(String idPauta) {
        Pauta pauta = this.getPauta(idPauta);
        Map<OpcaoVoto, Long> resultadoVotacao = pauta.obterResultado();
        long qtdVotosSim = Optional.ofNullable(resultadoVotacao.get(OpcaoVoto.SIM)).orElse(0L);
        long qtdVotosNao = Optional.ofNullable(resultadoVotacao.get(OpcaoVoto.NAO)).orElse(0L);
        ResultadoResponse response = ResultadoResponse.builder()
                .idPauta(idPauta)
                .titulo(pauta.getTitulo())
                .qtdVotosSim(qtdVotosSim)
                .qtdVotosNao(qtdVotosNao)
                .resultado(this.calcularResultadoFinal(qtdVotosSim, qtdVotosNao))
                .build();
        return response;
    }

    public ResultadoVotacao calcularResultadoFinal(long numVotosSim, long numVotosNao) {
        ResultadoVotacao resultado = ResultadoVotacao.EMPATE;
        if (numVotosSim > numVotosNao)
            resultado = ResultadoVotacao.SIM;
        if (numVotosSim < numVotosNao)
            resultado = ResultadoVotacao.NAO;
        return resultado;
    }

    public List<Pauta> getPautasByStatus(StatusPauta status) {
        return repository.findAllByStatus(status.toString());
    }
}
