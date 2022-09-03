package com.example.provafinal.Cardapio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.provafinal.Cardapio.entity.Cardapio;
import com.example.provafinal.Cardapio.service.CardapioService;
import com.example.provafinal.Prato.entity.Prato;
import com.example.provafinal.Responses.Response;

@RestController
@RequestMapping(value = "cardapio")
public class CardapioController {
    @Autowired
    CardapioService cardapioService;
    
    @PutMapping(value = "/{id}/vincularPrato")
    public ResponseEntity<Cardapio> vincularPrato(@RequestBody Prato prato, @PathVariable("id") Long id) {
        if(prato.getId()!= null) {
            Optional<Cardapio> cardapio = cardapioService.consultar(id);
            cardapio.ifPresent(cardapioConsultado -> {
                if (cardapioConsultado.getPratos() != null) {
                    List<Prato> pratos = cardapioConsultado.getPratos();
                    if (!pratos.contains(prato)) {
                        pratos.add(prato);
                        cardapioService.editar(cardapioConsultado);
                    }
                }
            });
            return ResponseEntity.of(cardapio);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}/removerPrato")
    public ResponseEntity<Cardapio> removerPrato(@RequestBody Prato prato, @PathVariable("id") Long id) {
        Optional<Cardapio> cardapio = cardapioService.consultar(id);
        cardapio.ifPresent(cardapioConsultado -> {
            if(cardapioConsultado.getPratos() != null) {
                List<Prato> pratos = cardapioConsultado.getPratos();
                if(pratos.contains(prato)) {
                    pratos.remove(prato);
                    cardapioService.editar(cardapioConsultado);
                }
            }
        });

        return ResponseEntity.of(cardapio);
    }

    @GetMapping(value = "/listar")
    public List<Cardapio> listar() {
        return cardapioService.listar();
    }

    @PostMapping(value = "/incluir")
    public ResponseEntity<Response<Cardapio>> criar(@Valid @RequestBody Cardapio cardapio, BindingResult result) {
        Response<Cardapio> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        cardapio = cardapioService.adicionar(cardapio);
        response.setData(cardapio);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cardapio> excluir(@PathVariable("id") Long id) {
        cardapioService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/alterarDisponibilidade")
    public ResponseEntity<Cardapio> alterarDisponibilidade(@PathVariable("id") Long id, @RequestBody Cardapio cardapioJSON) {
        Optional<Cardapio> cardapio = cardapioService.consultar(id);
        
        cardapio.ifPresent(cardapioConsultado -> {
            cardapioConsultado.setDisponibilidade(cardapioJSON.getDisponibilidade());
            cardapioService.editar(cardapioConsultado);
        });
        
        return ResponseEntity.of(cardapio);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cardapio> consultar(@PathVariable("id") Long id) {
        return ResponseEntity.of(cardapioService.consultar(id));
        
    }
}
