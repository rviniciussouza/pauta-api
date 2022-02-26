package com.example.pautaapi.rest.request;

import javax.validation.constraints.NotEmpty;

import com.example.pautaapi.domain.OpcaoVoto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoRequest {
    @NotEmpty
    private String idAssociado;
    @NotEmpty
    private String cpfAssociado;
    private OpcaoVoto voto;
}
