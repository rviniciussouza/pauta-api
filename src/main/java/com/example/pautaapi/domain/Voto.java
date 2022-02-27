package com.example.pautaapi.domain;

import com.example.pautaapi.constants.OpcaoVoto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Voto {
    private String id;
    private String idAssociado;
    private String cpfAssociado;
    private OpcaoVoto voto;

    public Voto(String idAssociado, String cpfAssociado, OpcaoVoto voto) {
        this.idAssociado = idAssociado;
        this.cpfAssociado = cpfAssociado;
        this.voto = voto;
    }
}
