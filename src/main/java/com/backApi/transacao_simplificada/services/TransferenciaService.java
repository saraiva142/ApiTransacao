package com.backApi.transacao_simplificada.services;

import com.backApi.transacao_simplificada.controller.TransacaoDTO;
import com.backApi.transacao_simplificada.infrastructure.entity.Carteira;
import com.backApi.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.backApi.transacao_simplificada.infrastructure.entity.Transacoes;
import com.backApi.transacao_simplificada.infrastructure.entity.Usuario;
import com.backApi.transacao_simplificada.infrastructure.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

    private final UsuarioService usuarioService;
    private final AutorizacaoService autorizacaoService;
    private final CarteiraService carteiraService;
    private final TransacaoRepository repository;

    @Transactional
    public void transferirValores(TransacaoDTO transacaoDTO) {

        Usuario pagador = usuarioService.buscarUsuario(transacaoDTO.payer());

        Usuario recebedor = usuarioService.buscarUsuario(transacaoDTO.payee());

        validaPagadorLogista(pagador);

        validarSaldoUsuario(pagador, transacaoDTO.value());

        validarTransferencia();

        //Deu tudo ok, vamos salvar o novo saldo da carteira
        pagador.getCarteira().setSaldo(pagador.getCarteira().getSaldo().subtract(transacaoDTO.value()));
        atualizarSaldoCarteira(pagador.getCarteira());
        recebedor.getCarteira().setSaldo(pagador.getCarteira().getSaldo().add(transacaoDTO.value()));
        atualizarSaldoCarteira(recebedor.getCarteira());

        Transacoes transacoes = Transacoes.builder()
                .valor(transacaoDTO.value())
                .pagador(pagador)
                .recebedor(recebedor)
                .build();

        repository.save(transacoes);

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

    private  void validarTransferencia() {
        try {
            if (!autorizacaoService.validarTransferencia()) {
                throw new IllegalArgumentException("Transação não autorizada pela api");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void atualizarSaldoCarteira(Carteira carteira) {
        carteiraService.salvar(carteira);
    }

}
