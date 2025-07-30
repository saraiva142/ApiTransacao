package com.backApi.transacao_simplificada.infrastructure.configs;

import com.backApi.transacao_simplificada.infrastructure.entity.Carteira;
import com.backApi.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.backApi.transacao_simplificada.infrastructure.entity.Usuario;
import com.backApi.transacao_simplificada.infrastructure.repository.CarteiraRepository;
import com.backApi.transacao_simplificada.infrastructure.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class PopularTabelaUsuario {

    @Bean
    CommandLineRunner popularBanco(UsuarioRepository usuarioRepository, CarteiraRepository carteiraRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                Usuario usuario1 = new Usuario(null, "Carlos da Silva", "carlos@email.com", "101111111111",
                        encoder.encode("123456"), null, TipoUsuario.COMMUM);

                Usuario usuario2 = new Usuario(null, "Ana Souza", "ana@email.com", "22222222222",
                        encoder.encode("123456"), null, TipoUsuario.COMMUM);

                Usuario usuario3 = new Usuario(null, "Loja Exemplo", "loja@email.com", "33333333333",
                        encoder.encode("123456"), null, TipoUsuario.LOJISTA);

                Usuario usuario4 = new Usuario(null, "Loja da Vó", "lojadavo@email.com", "4444444444",
                        encoder.encode("123456"), null, TipoUsuario.LOJISTA);

                Usuario usuario5 = new Usuario(null, "Joao Aquino", "joao@email.com", "555555555",
                        encoder.encode("123456"), null, TipoUsuario.COMMUM);

                usuarioRepository.saveAll(List.of(usuario1, usuario2, usuario3, usuario4, usuario5));

                Carteira carteira1 = new Carteira(null, new BigDecimal("1000.00"), usuario1);
                Carteira carteira2 = new Carteira(null, new BigDecimal("2000.00"), usuario2);
                Carteira carteira3 = new Carteira(null, new BigDecimal("5000.00"), usuario3);
                Carteira carteira4 = new Carteira(null, new BigDecimal("5000.00"), usuario4);
                Carteira carteira5 = new Carteira(null, new BigDecimal("10000.00"), usuario5);

                carteiraRepository.saveAll(List.of(carteira1, carteira2, carteira3, carteira4, carteira5));

                System.out.println("Usuários e carteiras populados com sucesso!");
            }
        };
    }
}
