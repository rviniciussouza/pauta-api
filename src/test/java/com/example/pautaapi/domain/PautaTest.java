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
    @DisplayName("Deve abrir a sessão de votação com sucesso")
    public void abrirSessao() {
        Pauta pauta = pautaSemSessao();
        pauta.abrirSessao(null);
        assertNotNull(pauta.getSessaoVotacao());
    }

    @Test
    @DisplayName("Deve lançar um SessaoJaIniciadaException")
    public void abrirSessaoAnteriormenteAberta() {
        Pauta pauta = pautaAberta();
        Throwable exception = Assertions.catchThrowable(
            () -> pauta.abrirSessao(null));
        assertThat(exception).isInstanceOf(SessaoJaIniciadaException.class);
        assertThat(exception.getMessage()).isEqualTo("A sessão da pauta 'id' já foi iniciada anteriormente.");
    }
}
