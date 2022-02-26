package com.example.pautaapi.domain;

import com.example.pautaapi.exception.SessaoJaIniciadaException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.pautaapi.stubs.PautaStub.pautaAberta;
import static com.example.pautaapi.stubs.PautaStub.pautaSemSessao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PautaTest {
    
    @Test
    @DisplayName("Deve abrir a seção de votação com sucesso")
    public void abrirSecao() {
        Pauta pauta = pautaSemSessao();
        pauta.abrirSecao(null);
        assertNotNull(pauta.getSessaoVotacao());
    }

    @Test
    @DisplayName("Deve lançar um SessaoJaIniciadaException")
    public void abrirSecaoAnteriormenteAberta() {
        Pauta pauta = pautaAberta();
        Throwable exception = Assertions.catchThrowable(
            () -> pauta.abrirSecao(null));
        assertThat(exception).isInstanceOf(SessaoJaIniciadaException.class);
        assertThat(exception.getMessage()).isEqualTo("A seção da pauta 'id' já foi iniciada anteriormente.");
    }
}
