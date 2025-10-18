package com.desafio.itau_islno.storage;

import com.desafio.itau_islno.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransacaoStorage {

    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void add(Transacao transacao){
        this.transacoes.add(transacao);
    }
}
