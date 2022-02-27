package com.example.pautaapi.exception;

import org.springframework.http.HttpStatus;

public class VotoDuplicadoException extends BussinesException{
    public VotoDuplicadoException(String msg) {
        super(String.format("O associado com id '%s' já votou nesta sessão.", msg));
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
