package com.desafio.ngbilling.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.desafio.ngbilling.exception.TransacaoException;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.service.impl.PixTransacaoService;

public class PixTransacaoServiceTest {

	@InjectMocks
	private PixTransacaoService pixTransacaoService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void transacaoDeveExecutarSemExcecaoQuandoSaldoSuficiente() {
		Conta conta = new Conta();
		conta.setNumeroConta(1);
		conta.setSaldo(100f);

		float valor = 50f;

		assertDoesNotThrow(() -> pixTransacaoService.transacao(conta, valor));
	}

	@Test
	void transacaoDeveLancarExcecaoQuandoSaldoInsuficiente() {
		Conta conta = new Conta();
		conta.setNumeroConta(1);
		conta.setSaldo(10f);

		float valor = 20f;

		assertThrows(TransacaoException.class, () -> pixTransacaoService.transacao(conta, valor));
	}
}
