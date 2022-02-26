package com.example.pautaapi.stubs;

import java.time.LocalDateTime;

import com.example.pautaapi.domain.SessaoVotacao;

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
}
