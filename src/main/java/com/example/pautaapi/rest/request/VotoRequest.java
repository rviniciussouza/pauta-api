package com.example.pautaapi.rest.request;

import javax.validation.constraints.NotEmpty;

import com.example.pautaapi.constants.OpcaoVoto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VotoRequest {
    @ApiModelProperty(notes = "Identificador do associado", required = true)
    @NotEmpty
    private String idAssociado;
    
    @ApiModelProperty(notes = "CPF do associado", required = true)
    @NotEmpty
    private String cpfAssociado;
    
    @ApiModelProperty(notes = "Voto", required = true)
    private OpcaoVoto voto;
}
