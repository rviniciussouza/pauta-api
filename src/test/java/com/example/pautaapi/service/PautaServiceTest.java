package com.example.pautaapi.service;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.exception.PautaNaoEncontradaException;
import com.example.pautaapi.repository.PautaRepository;
import com.example.pautaapi.service.impl.PautaServiceImpl;

import static com.example.pautaapi.stubs.PautaStub.pautaAberta;
import static com.example.pautaapi.stubs.PautaStub.pautaSemSessao;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Captor
    private ArgumentCaptor<Pauta> pautaArgumentCaptor;

    @BeforeEach 
    public void setUp() {
        this.service = new PautaServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve criar uma pauta com sucesso")
    public void criarPauta() {
        service.criarPauta(pautaSemSessao());
        verify(repository, Mockito.times(1)).save(pautaSemSessao());
    }

    @Test
    @DisplayName("Deve iniciar a seção de votação sucesso")
    public void abrirSessao() {
        when(repository.findById(any())).thenReturn(Optional.of(pautaSemSessao()));
        when(repository.save(any())).thenReturn(pautaAberta());
        service.abrirSessao("id", null);
        verify(repository, Mockito.times(1)).save(pautaArgumentCaptor.capture());
        assertNotNull(pautaArgumentCaptor.getValue().getTempoLimite());

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
}
