package com.desafio.ngbilling.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.desafio.ngbilling.dto.TransacaoDTO;
import com.desafio.ngbilling.exception.ContaExeption;
import com.desafio.ngbilling.exception.TransacaoException;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.model.Transacao;
import com.desafio.ngbilling.repository.ContaRepository;
import com.desafio.ngbilling.repository.TransacaoRepository;
import com.desafio.ngbilling.service.impl.TransacaoService;

public class TransacaoServiceTest {

	@InjectMocks
	TransacaoService transacaoService;

	@Mock
	private ContaRepository contaRepository;

	@Mock
	private TransacaoRepository transacaoRepository;

	@Mock
	private Map<String, ITransacao> transacaoMap;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void realizarTransacaoSucesso() {
		Conta conta = new Conta();
		conta.setNumeroConta(1);
		conta.setSaldo(100f);

		Transacao transacao = new Transacao();
		transacao.setConta(conta);
		transacao.setFormaPagamento("P");
		transacao.setValor(10.00f);

		TransacaoDTO dto = new TransacaoDTO("P", 1, 50f);

		when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.of(conta));
		when(contaRepository.save(any())).thenReturn(conta);
		when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

		ITransacao iTransacaoMock = mock(ITransacao.class);
		when(transacaoMap.get("P")).thenReturn(iTransacaoMock);

		assertDoesNotThrow(() -> transacaoService.realizarTransacao(dto));
	}

	@Test
	void realizarTransacaoContaNaoEncontradaLancaContaExeption() {
		TransacaoDTO dto = new TransacaoDTO("P", 99, 50f);

		when(contaRepository.findByNumeroConta(99)).thenReturn(Optional.empty());

		assertThrows(ContaExeption.class, () -> transacaoService.realizarTransacao(dto));
	}

	@Test
	void realizarTransacao_formaPagamentoInvalida_lancaTransacaoException() {
		Conta conta = new Conta();
		conta.setNumeroConta(1);
		conta.setSaldo(100f);

		TransacaoDTO dto = new TransacaoDTO("INVALIDA", 1, 50f);

		when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.of(conta));

		assertThrows(TransacaoException.class, () -> transacaoService.realizarTransacao(dto));

	}

	@Test
	void realizarTransacaoComValorInvalidoDeveLancarExcecao() {
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumeroConta(1);
		assertThrows(TransacaoException.class, () -> transacaoService.realizarTransacao(dto));

		dto.setValor(0f);
		assertThrows(TransacaoException.class, () -> transacaoService.realizarTransacao(dto));

		dto.setValor(-10f);
		assertThrows(TransacaoException.class, () -> transacaoService.realizarTransacao(dto));
	}

	@Test
	void toStringDeTransacaoDeveRetornarStringFormatada() {
		Conta conta = new Conta();
		conta.setId(UUID.randomUUID());
		conta.setNumeroConta(123);
		conta.setSaldo(500f);

		Transacao transacao = new Transacao();
		transacao.setConta(conta);
		transacao.setFormaPagamento("P");
		transacao.setValor(100f);

		String esperado = "Transacao [conta=" + conta.toString() + ", formaPagamento=P, valor=100.0]";
		assertEquals(esperado, transacao.toString());
	}

	@Test
	void testConstrutorEGettersDaTransacao() {
		Conta conta = new Conta();
		conta.setId(UUID.randomUUID());
		conta.setNumeroConta(123);
		conta.setSaldo(1000f);
		
		Transacao transacao = new Transacao(conta, "P", 500f);
		UUID id = UUID.randomUUID();
		transacao.setId(id);
		
		assertEquals(id, transacao.getId());
	}
}
