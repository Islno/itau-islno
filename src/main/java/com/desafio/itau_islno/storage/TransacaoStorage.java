package com.desafio.itau_islno.storage;

import com.desafio.itau_islno.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransacaoStorage {

    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void add(Transacao transacao){
        System.out.println("adicionando transação...");
        this.transacoes.add(transacao);
        System.out.println("Transação adicionada! Total de transações: " + this.transacoes.size());
    }
    public void deleteAll(){
        System.out.println("Limpando a lista... Total de transações antes: " + this.transacoes.size());

        this.transacoes.clear();

        System.out.println("Lista limpa! Total de transações agora: " + this.transacoes.size());
    }
    public List<Transacao> getTransacoes(){
        return new CopyOnWriteArrayList<>(transacoes); //Retorna uma cópia para evitar modificações externas
    }
}
