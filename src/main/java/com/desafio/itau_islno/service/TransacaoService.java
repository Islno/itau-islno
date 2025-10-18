package com.desafio.itau_islno.service;

import com.desafio.itau_islno.model.Transacao;
import com.desafio.itau_islno.storage.TransacaoStorage;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoStorage transacaoStorage;

    public void registrarTransacao(Transacao transacao) {
        transacaoStorage.add(transacao);
    }
}
