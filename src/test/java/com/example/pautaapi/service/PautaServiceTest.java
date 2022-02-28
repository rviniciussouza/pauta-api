package com.example.pautaapi.service;

import com.example.pautaapi.constants.OpcaoVoto;
import com.example.pautaapi.constants.ResultadoVotacao;
import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.domain.Voto;
import com.example.pautaapi.exception.PautaNaoEncontradaException;
import com.example.pautaapi.exception.SessaoFinalizadaExcepetion;
import com.example.pautaapi.exception.SessaoNaoEncontrada;
import com.example.pautaapi.repository.PautaRepository;
import com.example.pautaapi.rest.response.ResultadoResponse;
import com.example.pautaapi.service.impl.PautaServiceImpl;
import com.example.pautaapi.stubs.PautaStub;

import static com.example.pautaapi.stubs.PautaStub.pautaAberta;
import static com.example.pautaapi.stubs.PautaStub.pautaComVotosSim;
import static com.example.pautaapi.stubs.PautaStub.pautaEncerrada;
import static com.example.pautaapi.stubs.PautaStub.pautaSemSessao;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PautaServiceTest {
    
    PautaService service;

    @MockBean
    PautaRepository repository;

    @MockBean
    CpfService cpfService;

    @Captor
    private ArgumentCaptor<Pauta> pautaArgumentCaptor;

    Voto voto;

    @BeforeEach 
    public void setUp() {
        this.service = new PautaServiceImpl(repository, cpfService);
        voto = new Voto("idAssociado", "CPF", OpcaoVoto.SIM);
    }

    @Test
    @DisplayName("Deve criar uma pauta com sucesso")
    public void criarPauta() {
        service.salvar(pautaSemSessao());
        verify(repository, Mockito.times(1)).save(pautaSemSessao());
    }

    @Test
    @DisplayName("Deve iniciar a sessão de votação sucesso")
    public void abrirSessao() {
        when(repository.findById(any())).thenReturn(Optional.of(pautaSemSessao()));
        when(repository.save(any())).thenReturn(pautaAberta());
        service.abrirSessao("id", null);
        verify(repository, Mockito.times(1)).save(pautaArgumentCaptor.capture());
        assertNotNull(pautaArgumentCaptor.getValue().getSessaoVotacao());

    }

    @Test
    @DisplayName("Deve obter uma excessão PautaNaoEncontradaException")
    public void abrirSessaoDeUmaPautaInexistente() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        Throwable exception = Assertions.catchThrowable(
            () -> service.abrirSessao("id", null));
        assertThat(exception).isInstanceOf(PautaNaoEncontradaException.class);
        assertThat(exception.getMessage()).isEqualTo("Pauta 'id' não foi encontrada.");
    }

    @Test
    @DisplayName("Deve obter uma excessão SessaoNaoAbertaException")             
    public void votoEmSessaoNaoIniciada() {
        when(repository.findById(any())).thenReturn(Optional.of(pautaSemSessao()));
        Throwable exception = Assertions.catchThrowable(
            () -> service.adicionarVoto("idPauta", this.voto));
        assertThat(exception).isInstanceOf(SessaoNaoEncontrada.class);
    }

    @Test
    @DisplayName("Deve obter uma excessão SessaoFinalizadaExcepetion")             
    public void votoEmSessaoEncerrada() {
        when(repository.findById(any())).thenReturn(Optional.of(pautaEncerrada()));
        Throwable exception = Assertions.catchThrowable(
            () -> service.adicionarVoto("idPauta", this.voto));
        assertThat(exception).isInstanceOf(SessaoFinalizadaExcepetion.class);
    }

    @Test
    @DisplayName("Deve contabilizar e retornar o resultado de uma votação")             
    public void obterResultadoVotacao() {
        when(repository.findById(any())).thenReturn(Optional.of(pautaComVotosSim()));
        ResultadoResponse resultado = service.obterResultadoVotacao("id");
        assertNotNull(resultado.getTitulo());
        assertEquals(3, resultado.getQtdVotosSim());
        assertEquals(1, resultado.getQtdVotosNao());
        assertEquals(ResultadoVotacao.SIM, resultado.getResultado());
    }

    @Test
    @DisplayName("Deve contabilizar e retornar o resultado de uma votação")             
    public void obterResultadoVotacaoComEmpate() {
        when(repository.findById(any())).thenReturn(Optional.of(PautaStub.pautaComEmpateDeVotos()));
        ResultadoResponse resultado = service.obterResultadoVotacao("id");
        assertNotNull(resultado.getTitulo());
        assertEquals(2, resultado.getQtdVotosSim());
        assertEquals(2, resultado.getQtdVotosNao());
        assertEquals(ResultadoVotacao.EMPATE, resultado.getResultado());
    }
}
