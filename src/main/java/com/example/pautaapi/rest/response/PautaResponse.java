package com.example.pautaapi.rest.response;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaResponse {
    @NotEmpty
    private String titulo;
    private String id;
}