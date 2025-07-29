package com.backApi.transacao_simplificada.services;

import com.backApi.transacao_simplificada.infrastructure.entity.Carteira;
import com.backApi.transacao_simplificada.infrastructure.repository.CarteiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarteiraService {

    private final CarteiraRepository repository;

    public void salvar(Carteira carteira) {
        repository.save(carteira);
    }
}
