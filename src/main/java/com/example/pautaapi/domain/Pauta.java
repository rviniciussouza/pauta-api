package com.example.pautaapi.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.example.pautaapi.exception.SessaoJaIniciadaException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Pauta {
    @Id
    private String id;
    private String titulo;
    private LocalDateTime tempoLimite;
    private List<Voto> votos;

    public Pauta abrirSecao(Integer minutos) {
        if(this.tempoLimite != null) {
            throw new SessaoJaIniciadaException(id);
        }
        this.tempoLimite = definirTempoLimite(minutos);
        return this;
    }

    public LocalDateTime definirTempoLimite(Integer minutos) {
        return LocalDateTime.now().plusMinutes(
            minutos == null ? 1 : minutos
        );
    }
}
