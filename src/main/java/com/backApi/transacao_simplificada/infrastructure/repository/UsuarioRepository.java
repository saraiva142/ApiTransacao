package com.backApi.transacao_simplificada.infrastructure.repository;

import com.backApi.transacao_simplificada.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUserByEmail(String email);
    Optional<Usuario> findUserBycpfCnpj(String cpfCnpj);
}
