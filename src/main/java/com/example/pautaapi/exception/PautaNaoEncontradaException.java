package com.example.pautaapi.exception;

public class PautaNaoEncontradaException extends BussinesException {
    public PautaNaoEncontradaException(String msg) {
        super(String.format("Pauta '%s' não foi encontrada.", msg));
    }
}
