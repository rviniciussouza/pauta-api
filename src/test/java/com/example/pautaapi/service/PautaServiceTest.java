package com.example.pautaapi.service;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.repository.PautaRepository;
import com.example.pautaapi.service.impl.PautaServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PautaServiceTest {
    
    PautaService service;

    @MockBean
    PautaRepository repository;

    @BeforeEach 
    public void setUp() {
        this.service = new PautaServiceImpl(repository);
    }

    @Test
    public void criarPauta() {
        Pauta pauta = Pauta.builder().titulo("titulo").build();
        BDDMockito.given(repository.save(pauta)).willReturn(
            Pauta.builder().id("id").titulo("titulo").build()
        );
        Pauta pautaCriada = service.criarPauta(pauta);
        assertThat(pautaCriada.getId()).isNotNull();
        assertThat(pautaCriada.getTitulo()).isEqualTo(pauta.getTitulo());
    }
}
