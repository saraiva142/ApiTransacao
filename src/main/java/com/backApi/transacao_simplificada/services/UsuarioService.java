package com.backApi.transacao_simplificada.services;

import com.backApi.transacao_simplificada.infrastructure.entity.Usuario;
import com.backApi.transacao_simplificada.infrastructure.exceptions.UserNotFound;
import com.backApi.transacao_simplificada.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario buscarUsuario(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFound("Usuário não encontrado"));

    }
}
