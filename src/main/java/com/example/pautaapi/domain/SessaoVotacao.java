package com.example.pautaapi.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessaoVotacao {
    private static Integer TEMPO_DEFAULT_MINUTOS = 1; 
    private LocalDateTime tempoLimite;
    private List<Voto> votos;

    public SessaoVotacao(Integer tempoMinutos) {
        this.tempoLimite = definirTempoLimite(tempoMinutos);
    }

    public LocalDateTime definirTempoLimite(Integer minutos) {
        return LocalDateTime.now().plusMinutes(
            minutos == null ? TEMPO_DEFAULT_MINUTOS : minutos
        );
    }
}
