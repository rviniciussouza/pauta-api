package com.example.pautaapi.exception;

public class SessaoNaoEncontrada extends BussinesException{
    public SessaoNaoEncontrada(String msg) {
        super(String.format("Sessão da pauta '%s' não encontrada.", msg));
    }
}
