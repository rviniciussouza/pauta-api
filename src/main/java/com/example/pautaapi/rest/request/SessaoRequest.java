package com.example.pautaapi.rest.request;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SessaoRequest {
    @Min(value = 1, message = "O tempo da sessão deve ser no mínimo de 1 minuto")
    private Integer minutos;
}
