package com.example.pautaapi.rest.request;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessaoRequest {
    @ApiModelProperty(notes = "Duração da sessão em minutos", required = false)
    @Min(value = 1, message = "O tempo da sessão deve ser no mínimo de 1 minuto")
    private Integer minutos;
}
