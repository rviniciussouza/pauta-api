package com.example.pautaapi.stubs;

import static com.example.pautaapi.stubs.SessaoStub.sessaoComEmpateDeVotos;
import static com.example.pautaapi.stubs.SessaoStub.sessaoComVotosSIM;
import static com.example.pautaapi.stubs.SessaoStub.sessaoVotacaoDefault;
import static com.example.pautaapi.stubs.SessaoStub.sessaoVotacaoEncerrada;

import com.example.pautaapi.domain.Pauta;

public class PautaStub {
    
    public static Pauta pautaAberta() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .sessaoVotacao(sessaoVotacaoDefault())
            .build();
    }

    public static Pauta pautaEncerrada() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .sessaoVotacao(sessaoVotacaoEncerrada())
            .build();
    }

    public static Pauta pautaSemSessao() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .build();
    }

    public static Pauta pautaComVotosSim() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .sessaoVotacao(sessaoComVotosSIM())
            .build();
    }

    public static Pauta pautaComEmpateDeVotos() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .sessaoVotacao(sessaoComEmpateDeVotos())
            .build();
    }
}
