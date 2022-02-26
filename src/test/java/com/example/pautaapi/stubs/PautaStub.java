package com.example.pautaapi.stubs;

import java.time.LocalDateTime;

import com.example.pautaapi.domain.Pauta;

public class PautaStub {
 
    public static Pauta pautaAberta() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .tempoLimite(LocalDateTime.now())
            .build();
    }

    public static Pauta pautaEncerrada() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .tempoLimite(LocalDateTime.now().minusMinutes(1))
            .build();
    }

    public static Pauta pautaSemSessao() {
        return Pauta.builder().id("id")
            .titulo("titulo")
            .build();
    }
}
