package com.example.pautaapi.exception;

import org.springframework.http.HttpStatus;

public class SessaoFinalizadaExcepetion extends BussinesException{
    public SessaoFinalizadaExcepetion() {
        super("A sessão de votação para esta pauta já foi encerrada.");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
