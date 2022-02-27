package com.example.pautaapi.rest.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaResponse {
    @ApiModelProperty(notes = "Título da pauta")
    private String titulo;
    @ApiModelProperty(notes = "Identificador único da pauta")
    private String id;
}