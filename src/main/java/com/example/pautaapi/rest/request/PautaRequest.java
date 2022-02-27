package com.example.pautaapi.rest.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaRequest {
    @ApiModelProperty(notes = "Título da pauta", required = true)
    @NotEmpty
    private String titulo;
}
