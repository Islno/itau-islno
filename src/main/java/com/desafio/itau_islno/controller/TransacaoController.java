package com.desafio.itau_islno.controller;

import com.desafio.itau_islno.model.Transacao;
import com.desafio.itau_islno.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transacao")
    public ResponseEntity<Void> criar(@Valid @RequestBody Transacao transacao) {
        transacaoService.registrarTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
