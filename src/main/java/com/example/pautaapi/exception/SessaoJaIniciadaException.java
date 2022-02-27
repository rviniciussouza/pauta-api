package com.example.pautaapi.exception;

import org.springframework.http.HttpStatus;

public class SessaoJaIniciadaException extends BussinesException {
    public SessaoJaIniciadaException(String msg) {
        super(String.format("A sessão da pauta '%s' já foi iniciada anteriormente.", msg));
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
