package com.backApi.transacao_simplificada.infrastructure.exceptions;

public class UserNotFound extends RuntimeException {

    public UserNotFound(String mensagem) {
        super(mensagem);
    }
}
