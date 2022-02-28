package com.example.pautaapi.domain;

import java.time.LocalDateTime;
import java.util.Map;

import com.example.pautaapi.constants.OpcaoVoto;
import com.example.pautaapi.exception.SessaoFinalizadaExcepetion;
import com.example.pautaapi.stubs.SessaoStub;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SessaoVotacaoTest {
    
    @Test
    @DisplayName("Deve definir o tempo padrão de 1 minuto")
    public void definirTempoLimiteDefault() {
        SessaoVotacao sessao = new SessaoVotacao();
        LocalDateTime tempo = sessao.definirTempoLimite(null);
        assertThat(tempo).isBefore(LocalDateTime.now().plusMinutes(1));
    }

    @Test
    @DisplayName("Deve obter uma excessão SessaoFinalizadaExcepetion")
    public void adicionarVotoEmSessaoFinalizada() {
        SessaoVotacao sessao = SessaoStub.sessaoVotacaoEncerrada();
        Throwable exception = Assertions.catchThrowable(
            () -> sessao.votar(new Voto()));
        assertThat(exception).isInstanceOf(SessaoFinalizadaExcepetion.class);
    }

    @Test
    @DisplayName("Deve obter true")
    public void associadoJaVotou() {
        /* Contém um voto para o associado de id 1 */
        SessaoVotacao sessao = SessaoStub.sessaoComVotosSIM();
        boolean jaVotou = sessao.associadoJaVotou("1");
        assertThat(jaVotou);
    }

    @Test
    @DisplayName("Prazo de votação encerrado")
    public void sessaoFinalizada() {
        SessaoVotacao sessao = SessaoStub.sessaoVotacaoEncerrada();
        boolean finalizado = sessao.sessaoFinalizada();
        assertTrue(finalizado);
    }

    @Test
    public void resultadoApenasComVotosSim() {
        SessaoVotacao sessao = SessaoStub.sessaoApenasComVotosSim();
        Map<OpcaoVoto, Long> qtdVotos = sessao.calcularResultado();
        assertEquals(4, qtdVotos.get(OpcaoVoto.SIM));
        assertNull(qtdVotos.get(OpcaoVoto.NAO));
    }

    @Test
    public void resultadoComEmpate() {
        SessaoVotacao sessao = SessaoStub.sessaoComEmpateDeVotos();
        Map<OpcaoVoto, Long> qtdVotos = sessao.calcularResultado();
        assertEquals(2, qtdVotos.get(OpcaoVoto.SIM));
        assertEquals(2, qtdVotos.get(OpcaoVoto.NAO));
    }
}
