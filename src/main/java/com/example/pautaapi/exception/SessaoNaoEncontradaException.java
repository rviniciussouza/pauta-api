package com.example.pautaapi.exception;

import org.springframework.http.HttpStatus;

public class SessaoNaoEncontradaException extends BussinesException{
    public SessaoNaoEncontradaException(String msg) {
        super(String.format("Sessão da pauta '%s' não encontrada.", msg));
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
