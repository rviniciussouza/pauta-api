package com.example.pautaapi.service;
import java.util.List;

import com.example.pautaapi.constants.StatusPauta;
import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.domain.Voto;
import com.example.pautaapi.rest.response.ResultadoResponse;
public interface PautaService {
    public Pauta salvar(Pauta pauta);
    public Pauta getPauta(String idPauta);
    public Pauta abrirSessao(String idPauta, Integer minutos);
    public Pauta encerrarSessao(String idPauta);
    public Pauta adicionarVoto(String idPauta, Voto voto);
    public ResultadoResponse obterResultadoVotacao(String pautaId);
    public List<Pauta> getPautasByStatus(StatusPauta status);
}
