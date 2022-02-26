package com.example.pautaapi.rest.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PautaRequest {
    @NotEmpty
    private String titulo;
}
