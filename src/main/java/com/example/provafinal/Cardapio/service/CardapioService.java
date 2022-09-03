package com.example.provafinal.Cardapio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.provafinal.Cardapio.entity.Cardapio;
import com.example.provafinal.Cardapio.repository.CardapioRepository;

@Service
public class CardapioService {
    @Autowired
    CardapioRepository cardapioRepository;

    public Cardapio adicionar(Cardapio prato) {
        return cardapioRepository.save(prato);
    }

    public Optional<Cardapio> consultar(Long id) {
        return cardapioRepository.findById(id);
    }

    public void excluir(Long id) {
        cardapioRepository.deleteById(id);
    }

    public List<Cardapio> listar() {
        return cardapioRepository.findAll();
    }

    public Cardapio editar(Cardapio prato) {
        return cardapioRepository.save(prato);
    }
    
    
}
