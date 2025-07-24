package com.backApi.transacao_simplificada.services;

import com.backApi.transacao_simplificada.controller.TransacaoDTO;
import com.backApi.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.backApi.transacao_simplificada.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

    private final UsuarioService usuarioService;

    public void transferirValores(TransacaoDTO transacaoDTO) {

        Usuario pagador = usuarioService.buscarUsuario(transacaoDTO.payer());

        Usuario recebedor = usuarioService.buscarUsuario(transacaoDTO.payee());

        validaPagadorLogista(pagador);

        validarSaldoUsuario(pagador, transacaoDTO.value());



    }

    private void validaPagadorLogista(Usuario usuario) {
        try {
            if (usuario.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
                throw new IllegalArgumentException("Transação não autorizada para esse tipo de usuário");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validarSaldoUsuario(Usuario usuario, BigDecimal valor) {
        try {
            if (usuario.getCarteira().getSaldo().compareTo(valor) < 0) {
                throw new IllegalArgumentException("Transação não autorizada! Saldo insuficiente!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
