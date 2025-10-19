package com.desafio.itau_islno.service;

import com.desafio.itau_islno.model.Estatistica;
import com.desafio.itau_islno.model.Transacao;
import com.desafio.itau_islno.storage.TransacaoStorage;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoStorage transacaoStorage;

    public void registrarTransacao(Transacao transacao) {
        transacaoStorage.add(transacao);
    }

    public void deletarTransacoes() {
        transacaoStorage.deleteALL();
    }

    public Estatistica calcularEstatisticas() {
        OffsetDateTime agora = OffsetDateTime.now();
        OffsetDateTime limiteInferior = agora.minusSeconds(60);

        DoubleSummaryStatistics stats = transacaoStorage.getTransacoes().stream().filter(t -> t.getDataHora().isAfter(limiteInferior)).mapToDouble(t -> t.getValor().doubleValue()).summaryStatistics();

        if (stats.getCount() == 0){
            return new Estatistica();
        }
        return new Estatistica(
                stats.getCount(),
                BigDecimal.valueOf(stats.getSum()).setScale(2, RoundingMode.HALF_UP),

                BigDecimal.valueOf(stats.getAverage()).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(stats.getMin()).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(stats.getMax()).setScale(2, RoundingMode.HALF_UP)
        );
    }
}
