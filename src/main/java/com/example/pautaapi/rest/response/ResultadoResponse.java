package com.example.pautaapi.rest.response;

import java.io.Serializable;

import com.example.pautaapi.constants.ResultadoVotacao;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultadoResponse implements Serializable{
    @ApiModelProperty(notes = "Identificador único da pauta")
    private String idPauta;
    @ApiModelProperty(notes = "Título da pauta")
    private String titulo;
    @ApiModelProperty(notes = "Quantidade de votos 'SIM'")
    private Long qtdVotosSim;
    @ApiModelProperty(notes = "Quantidade de votos 'NAO'")
    private Long qtdVotosNao;
    @ApiModelProperty(notes = "Resultado da votação")
    private ResultadoVotacao resultado;
}
