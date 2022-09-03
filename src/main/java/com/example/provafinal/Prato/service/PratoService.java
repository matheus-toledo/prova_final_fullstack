package com.example.provafinal.Prato.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.provafinal.Prato.entity.Prato;
import com.example.provafinal.Prato.repository.PratoRepository;

@Service
public class PratoService {
    @Autowired
    PratoRepository pratoRepository;
    
    public Prato adicionar(Prato prato) {
        return pratoRepository.save(prato);
    }
    
    public Optional<Prato> consultar(Long id) {
        return pratoRepository.findById(id);
    }
    
    public void excluir(Prato prato) {
        pratoRepository.removerVinculoPrato(prato.getId());
        pratoRepository.delete(prato);
    }
    
    public List<Prato> listar() {
        return pratoRepository.findAll();
    }
    
    public Prato editar(Prato prato) {
        return pratoRepository.save(prato);
    }
}
