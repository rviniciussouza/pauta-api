package com.example.pautaapi.domain;

import java.util.Map;
import java.util.Optional;

import com.example.pautaapi.constants.OpcaoVoto;
import com.example.pautaapi.exception.SessaoJaIniciadaException;
import com.example.pautaapi.exception.SessaoNaoEncontrada;

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
            throw new SessaoNaoEncontrada(id);
        }
        this.sessaoVotacao.votar(voto);
        return this;
    }

    public Map<OpcaoVoto, Long> obterResultado() {
        return Optional.ofNullable(sessaoVotacao)
            .map(SessaoVotacao::calcularResultado)
            .orElseThrow(() -> new SessaoNaoEncontrada(id));
    }
}
