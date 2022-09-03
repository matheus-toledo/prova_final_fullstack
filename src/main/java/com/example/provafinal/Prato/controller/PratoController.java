package com.example.provafinal.Prato.controller;

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

import com.example.provafinal.Prato.entity.Prato;
import com.example.provafinal.Prato.service.PratoService;
import com.example.provafinal.Responses.Response;

@RestController
@RequestMapping(value = "prato")
public class PratoController {
    @Autowired
    PratoService pratoService;

    @PostMapping(value = "/incluir")
    public ResponseEntity<Response<Prato>> adicionar(@Valid @RequestBody Prato prato, BindingResult result) {
        Response<Prato> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        prato = pratoService.adicionar(prato);
        response.setData(prato);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Prato> excluir(@PathVariable("id") Long id) {
        Optional<Prato> prato = pratoService.consultar(id);

        prato.ifPresent(pratoConsultado -> pratoService.excluir(pratoConsultado));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/listar")
    public List<Prato> listar() {
        return pratoService.listar();

    }

    @PutMapping(value = "/editar")
    public ResponseEntity<Response<Prato>> editar(@Valid @RequestBody Prato prato, BindingResult result) {
        Response<Prato> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        prato = pratoService.editar(prato);
        response.setData(prato);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Prato> consultar(@PathVariable("id") Long id) {
        Optional<Prato> prato = pratoService.consultar(id);

        if (prato.isPresent())
            return ResponseEntity.of(prato);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
