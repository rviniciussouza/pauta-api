package com.example.pautaapi.service;
import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.domain.Voto;

public interface PautaService {
    public Pauta criarPauta(Pauta pauta);
    public Pauta getPauta(String idPauta);
    public Pauta abrirSessao(String idPauta, Integer minutos);
    public Pauta adicionarVoto(String idPauta, Voto voto);
}
