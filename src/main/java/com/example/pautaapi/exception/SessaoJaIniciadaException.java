package com.example.pautaapi.exception;

public class SessaoJaIniciadaException extends BussinesException {
    public SessaoJaIniciadaException(String msg) {
        super(String.format("A seção da pauta '%s' já foi iniciada anteriormente.", msg));
    }
}
