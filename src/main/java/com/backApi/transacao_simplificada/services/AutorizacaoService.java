package com.backApi.transacao_simplificada.services;

import com.backApi.transacao_simplificada.infrastructure.clients.AutorizacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AutorizacaoService {

    private final AutorizacaoClient client;

    public boolean validarTransferencia() {
        if (Objects.equals(client.validarAutorizacao().data().authorization(), "true")) {
            return true;
        }
        return false;
    }

}
