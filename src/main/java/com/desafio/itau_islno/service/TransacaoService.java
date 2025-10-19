package com.desafio.itau_islno.service;

import com.desafio.itau_islno.model.Estatistica;
import com.desafio.itau_islno.model.Transacao;
import com.desafio.itau_islno.storage.TransacaoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoStorage transacaoStorage;

    public void registrarTransacao(Transacao transacao) {
        transacaoStorage.add(transacao);
    }

    public void deletarTransacoes() {
        transacaoStorage.deleteAll();
    }

    public Estatistica calcularEstatisticas() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limiteInferior = agora.minusSeconds(60);

        List<Transacao> transacoesRecentes = transacaoStorage.getTransacoes().stream()
                .filter(t -> t.getDataHora().isAfter(limiteInferior))
                .toList();

        if (transacoesRecentes.isEmpty()) {
            return new Estatistica();
        }

        BigDecimal sum = transacoesRecentes.stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avg = sum.divide(new BigDecimal(transacoesRecentes.size()), 2, RoundingMode.HALF_UP);

        BigDecimal min = transacoesRecentes.stream()
                .map(Transacao::getValor)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal max = transacoesRecentes.stream()
                .map(Transacao::getValor)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        long count = transacoesRecentes.size();

        return new Estatistica(count, sum, avg, min, max);
    }
}