package com.example.pautaapi.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SessaoVotacaoTest {
    
    @Test
    @DisplayName("Deve definir o tempo padrão de 1 minuto")
    public void definirTempoLimiteDefault() {
        SessaoVotacao sessao = new SessaoVotacao();
        LocalDateTime tempo = sessao.definirTempoLimite(null);
        assertThat(tempo).isBefore(LocalDateTime.now().plusMinutes(1));
    }
}
