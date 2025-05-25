package com.desafio.ngbilling.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.desafio.ngbilling.exception.TransacaoException;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.service.impl.CreditoTransacaoService;

public class CreditoTransacaoServiceTest {

	@InjectMocks
	CreditoTransacaoService creditoTransacaoService;
	
	@Mock
	Conta conta;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void transacao() {

		Conta conta = new Conta();
		conta.setNumeroConta(123);
		conta.setSaldo(50.00f);
		float valor = 40f;
		assertDoesNotThrow(() -> creditoTransacaoService.transacao(conta, valor));
	}

	@Test
	void transacaoComSaldoInsuficienteDeveLancarExcecao() {
		
		when(conta.getSaldo()).thenReturn(100f);
		assertThrows(TransacaoException.class, () -> creditoTransacaoService.transacao(conta, 100f));	
	}
}
