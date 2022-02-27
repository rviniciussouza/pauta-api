package com.example.pautaapi.rest.response;

import com.example.pautaapi.constants.ResultadoVotacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultadoResponse {
    private String idPauta;
    private String titulo;
    private Long qtdVotosSim;
    private Long qtdVotosNao;
    private ResultadoVotacao resultado;
}
