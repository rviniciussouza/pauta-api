package com.example.pautaapi.domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.pautaapi.constants.OpcaoVoto;
import com.example.pautaapi.exception.SessaoFinalizadaExcepetion;
import com.example.pautaapi.exception.VotoDuplicadoException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessaoVotacao {
    private static Integer TEMPO_DEFAULT_MINUTOS = 1; 
    private LocalDateTime tempoLimite;
    private List<Voto> votos = new ArrayList<>();

    public SessaoVotacao(Integer tempoMinutos) {
        this.tempoLimite = definirTempoLimite(tempoMinutos);
    }

    public LocalDateTime definirTempoLimite(Integer minutos) {
        return LocalDateTime.now().plusMinutes(
            minutos == null ? TEMPO_DEFAULT_MINUTOS : minutos
        );
    }

    public void votar(Voto voto) {
        if(this.sessaoFinalizada()) {
            throw new SessaoFinalizadaExcepetion();
        }
        this.adicionarVoto(voto);
    }

    private void adicionarVoto(Voto voto) {
        if(this.associadoJaVotou(voto.getIdAssociado())) {
            throw new VotoDuplicadoException(voto.getIdAssociado());
        }
        this.votos.add(voto);
    }

    public boolean associadoJaVotou(String idAssociado) {
        return this.votos.stream()
            .anyMatch(voto -> voto.getIdAssociado().equals(idAssociado));
    }

    public boolean sessaoFinalizada() {
        return LocalDateTime.now().isAfter(this.tempoLimite);
    }

    public Map<OpcaoVoto, Long> contabilizarResultado() {
        return this.votos
            .stream()
            .collect(groupingBy(Voto::getVoto, counting()));
    }
}
