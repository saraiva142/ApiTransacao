package com.backApi.transacao_simplificada.controller;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal value, Long payer, Long payee ) {
}
