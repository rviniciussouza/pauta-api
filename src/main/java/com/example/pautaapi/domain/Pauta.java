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
    private SessaoVotacao sessaoVotacao;

    public Pauta abrirSecao(Integer minutos) {
        if(this.sessaoVotacao != null) {
            throw new SessaoJaIniciadaException(id);
        }
        this.sessaoVotacao = new SessaoVotacao(minutos);
        return this;
    }
}
