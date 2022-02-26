package com.example.pautaapi.domain;

import java.time.LocalDateTime;

import com.example.pautaapi.exception.SessaoJaIniciadaException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PautaTest {
    
    @Test
    @DisplayName("Deve abrir a seção de votação com sucesso")
    public void abrirSecao() {
        Pauta pauta = Pauta.builder().id("id").build();
        Integer doisMinutos = 2;
        pauta.abrirSecao(doisMinutos);
        assertThat(pauta.getTempoLimite()).isNotNull();
    }

    @Test
    @DisplayName("Deve lançar um SessaoJaIniciadaException")
    public void abrirSecaoAnteriormenteAberta() {
        Pauta pauta = Pauta.builder().id("id").tempoLimite(LocalDateTime.now()).build();
        Integer doisMinutos = 2;
        Throwable exception = Assertions.catchThrowable(
            () -> pauta.abrirSecao(doisMinutos));
        assertThat(exception).isInstanceOf(SessaoJaIniciadaException.class);
        assertThat(exception.getMessage()).isEqualTo("A seção da pauta 'id' já foi iniciada anteriormente.");
    }

    @Test
    @DisplayName("Deve definir o tempo padrão de 1 minuto")
    public void definirTempoLimiteDefault() {
        Pauta pauta = new Pauta();
        LocalDateTime tempo = pauta.definirTempoLimite(null);
        assertThat(tempo).isBefore(LocalDateTime.now().plusMinutes(1));
    }
}
