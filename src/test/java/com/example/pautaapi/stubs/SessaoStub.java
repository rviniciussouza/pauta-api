package com.example.pautaapi.stubs;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.example.pautaapi.constants.OpcaoVoto;
import com.example.pautaapi.domain.SessaoVotacao;
import com.example.pautaapi.domain.Voto;

public class SessaoStub {
    
    public static SessaoVotacao sessaoVotacao(int tempoMinutos) {
        return new SessaoVotacao(tempoMinutos);
    }

    public static SessaoVotacao sessaoVotacaoDefault() {
        return new SessaoVotacao(null);
    }

    public static SessaoVotacao sessaoVotacaoEncerrada() {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setTempoLimite(LocalDateTime.now().minusMinutes(1));
        return sessao;
    }

    public static SessaoVotacao sessaoComVotosSIM() {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setTempoLimite(LocalDateTime.now().minusMinutes(1));
        List<Voto> votos = Arrays.asList(
            new Voto("1", "CPF1", OpcaoVoto.SIM),
            new Voto("2", "CPF2", OpcaoVoto.NAO),
            new Voto("3", "CPF3", OpcaoVoto.SIM),
            new Voto("4", "CPF4", OpcaoVoto.SIM)
        );
        sessao.setVotos(votos);
        return sessao;
    }

    public static SessaoVotacao sessaoComEmpateDeVotos() {
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setTempoLimite(LocalDateTime.now().minusMinutes(1));
        List<Voto> votos = Arrays.asList(
            new Voto("1", "CPF1", OpcaoVoto.SIM),
            new Voto("2", "CPF2", OpcaoVoto.NAO),
            new Voto("3", "CPF3", OpcaoVoto.NAO),
            new Voto("4", "CPF4", OpcaoVoto.SIM)
        );
        sessao.setVotos(votos);
        return sessao;
    }
}
