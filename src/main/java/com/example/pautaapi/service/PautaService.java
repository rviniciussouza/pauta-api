package com.example.pautaapi.service;
import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.domain.Voto;
import com.example.pautaapi.rest.response.ResultadoResponse;
public interface PautaService {
    public Pauta criarPauta(Pauta pauta);
    public Pauta getPauta(String idPauta);
    public Pauta abrirSessao(String idPauta, Integer minutos);
    public Pauta adicionarVoto(String idPauta, Voto voto);
    public ResultadoResponse obterResultadoVotacao(String pautaId);
}
