package com.backApi.transacao_simplificada.services;

import com.backApi.transacao_simplificada.infrastructure.clients.AutorizacaoClient;
import com.backApi.transacao_simplificada.infrastructure.clients.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoClient client;

    public void enviarNotificacao() {
        client.enviarNotificacao();
    }

}
