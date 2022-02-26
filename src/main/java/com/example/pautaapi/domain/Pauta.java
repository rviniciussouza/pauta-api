package com.example.pautaapi.domain;

import com.example.pautaapi.exception.SessaoJaIniciadaException;
import com.example.pautaapi.exception.SessaoNaoAbertaException;

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

    public Pauta adicionarVoto(Voto voto) {
        if(this.sessaoVotacao == null) {
            throw new SessaoNaoAbertaException(id);
        }
        this.sessaoVotacao.votar(voto);
        return this;
    }
}
