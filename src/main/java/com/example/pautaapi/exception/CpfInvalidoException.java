package com.example.pautaapi.exception;

import static java.lang.String.format;

import org.springframework.http.HttpStatus;

public class CpfInvalidoException extends BussinesException {
    public CpfInvalidoException(String msg) {
        super(format("O cpf '%s' é inválido", msg));
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
