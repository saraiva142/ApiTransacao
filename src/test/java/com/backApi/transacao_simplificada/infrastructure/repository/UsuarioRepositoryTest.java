package com.backApi.transacao_simplificada.infrastructure.repository;

import com.backApi.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.backApi.transacao_simplificada.infrastructure.entity.Usuario;
import jakarta.persistence.EntityManager;
import org.apache.tomcat.util.buf.UEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityManager entityManager;

    @Nested
    class findUserByEmail {

        @Test
        @DisplayName("Should return user by email with success")
        void findUserByEmailWithSuccess() {

            //Arrange
            Usuario user = new Usuario(null, "Joao", "joao@email.com", "111111111",
                    "123456", null, TipoUsuario.COMMUM);

            usuarioRepository.save(user);

            //Act
            Optional<Usuario> result = usuarioRepository.findUserByEmail(user.getEmail());

            //Assert
            assert(result.isPresent());
            assertEquals(user.getEmail(), "joao@email.com");

        }

        @Test
        @DisplayName("Should return empty when user not found by email")
        void findUserByEmailNotFound() {

            //Arrange
            Usuario user = new Usuario(null, "Joao", "email@email.com", "111111111",
                    "123456", null, TipoUsuario.COMMUM);

            usuarioRepository.save(user);

            //Act
            Optional<Usuario> result = usuarioRepository.findUserByEmail("joao@email.com");

            //Assert
            assert(result.isEmpty());
        }


    }

    @Nested
    class findUserByCpfCnpj {

        @Test
        @DisplayName("Should return user by Cpf/Cnpj with success")
        void findUserByCpfCnpjWithSuccess() {
            //Arrange
            Long id = 1l;
            Usuario user = new Usuario();
            user.setId(id);
            user.setNomeCompleto("Joao");
            user.setEmail("email@email.com");
            user.setCpfCnpj("111111111");
            user.setSenha("123456");
            user.setCarteira(null);
            user.setTipoUsuario(TipoUsuario.COMMUM);

            usuarioRepository.save(user);

            //Act
            Optional<Usuario> result = usuarioRepository.findUserBycpfCnpj("11111111");

            //Assert
            assert(result.isPresent());
            assertEquals(user.getCpfCnpj(), "111111111");
        }

        @Test
        @DisplayName("Should return empty and null when user not found by Cpf/Cnpj")
        void findUserByCpfCnpjIsEmptyWhenNotFound() {
            //Arrange
            Long id = 1L;
            Usuario user = new Usuario();
            user.setId(id);
            user.setNomeCompleto("Joao");
            user.setEmail("email@email.com");
            user.setSenha("123456");
            user.setCarteira(null);
            user.setTipoUsuario(TipoUsuario.COMMUM);

            usuarioRepository.save(user);

            //Act
            Optional<Usuario> result = usuarioRepository.findUserBycpfCnpj(user.getCpfCnpj());

            //Arrange
            assertNull(result);
            assert(result.isEmpty());
        }

    }

    @Nested
    class findUserById {

    }
}