package com.example.pautaapi.exception;

public class VotoDuplicadoException extends BussinesException{
    public VotoDuplicadoException(String msg) {
        super(String.format("O associado com id '%s' já votou nesta sessão.", msg));
    }
}
