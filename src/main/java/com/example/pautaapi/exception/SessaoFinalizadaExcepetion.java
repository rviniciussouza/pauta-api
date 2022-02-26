package com.example.pautaapi.exception;

public class SessaoFinalizadaExcepetion extends BussinesException{
    public SessaoFinalizadaExcepetion() {
        super("Esta sessão de votação está encerrada.");
    }
}
