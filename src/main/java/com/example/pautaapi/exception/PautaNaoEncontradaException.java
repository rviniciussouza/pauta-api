package com.example.pautaapi.exception;

import org.springframework.http.HttpStatus;

public class PautaNaoEncontradaException extends BussinesException {
    public PautaNaoEncontradaException(String msg) {
        super(String.format("Pauta '%s' não foi encontrada.", msg));
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
