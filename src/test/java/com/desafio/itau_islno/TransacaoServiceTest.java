package com.desafio.itau_islno;

import com.desafio.itau_islno.model.Estatistica;
import com.desafio.itau_islno.model.Transacao;
import com.desafio.itau_islno.service.TransacaoService;
import com.desafio.itau_islno.storage.TransacaoStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransacaoServiceTest {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private TransacaoStorage transacaoStorage;

    @BeforeEach
    void setUp() {
        transacaoStorage.deleteAll();
    }

    @Test
    void deveCalcularEstatisticasCorretamenteComTransacoesRecentes() {
        // ARRANGE
        OffsetDateTime agora = OffsetDateTime.now();
        transacaoStorage.add(new Transacao(new BigDecimal("100.50"), agora.minusSeconds(10)));
        transacaoStorage.add(new Transacao(new BigDecimal("49.50"), agora.minusSeconds(20)));

        // ACT
        Estatistica resultado = transacaoService.calcularEstatisticas();

        // ASSERT
        assertEquals(2, resultado.getCount());
        assertEquals(0, new BigDecimal("150.00").compareTo(resultado.getSum()));
        assertEquals(0, new BigDecimal("75.00").compareTo(resultado.getAvg()));
        assertEquals(0, new BigDecimal("49.50").compareTo(resultado.getMin()));
        assertEquals(0, new BigDecimal("100.50").compareTo(resultado.getMax()));
    }

    @Test
    void deveIgnorarTransacoesAntigasNoCalculo() {
        // ARRANGE
        transacaoStorage.add(new Transacao(new BigDecimal("500.00"), OffsetDateTime.now().minusMinutes(5))); // Antiga
        transacaoStorage.add(new Transacao(new BigDecimal("10.00"), OffsetDateTime.now().minusSeconds(10))); // Recente

        // ACT
        Estatistica resultado = transacaoService.calcularEstatisticas();

        // ASSERT
        assertEquals(1, resultado.getCount());
        assertEquals(0, new BigDecimal("10.00").compareTo(resultado.getSum()));
    }

    @Test
    void deveRetornarEstatisticasZeradasQuandoNaoHaNenhumaTransacao() {
        // ARRANGE (a lista já está vazia)

        // ACT
        Estatistica resultado = transacaoService.calcularEstatisticas();

        // ASSERT
        assertEquals(0, resultado.getCount());
        assertEquals(0, BigDecimal.ZERO.setScale(2).compareTo(resultado.getSum()));
    }
}