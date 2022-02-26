package com.example.pautaapi.exception;

public class SessaoNaoAbertaException extends BussinesException{
    public SessaoNaoAbertaException(String msg) {
        super(String.format("A sessão de votação da pauta '%s' não está aberta.", msg));
    }
}
