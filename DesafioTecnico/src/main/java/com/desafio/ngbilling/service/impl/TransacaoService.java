package com.desafio.ngbilling.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.ngbilling.dto.TransacaoDTO;
import com.desafio.ngbilling.exception.ContaExeption;
import com.desafio.ngbilling.exception.TransacaoException;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.model.Transacao;
import com.desafio.ngbilling.repository.ContaRepository;
import com.desafio.ngbilling.repository.TransacaoRepository;
import com.desafio.ngbilling.service.ITransacao;

import jakarta.transaction.Transactional;

@Service
public class TransacaoService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private Map<String, ITransacao> transacaoMap;

	@Transactional
	public Conta realizarTransacao(TransacaoDTO dto) {
		Conta conta = validarTransacao(dto);

		String formaPagamento = dto.getFormaPagamento().toUpperCase();
		ITransacao transacaoService = transacaoMap.get(formaPagamento);

		transacaoService.transacao(conta, dto.getValor());

		contaRepository.save(conta);

		Transacao transacao = new Transacao(conta, dto.getFormaPagamento(), dto.getValor());
		transacaoRepository.save(transacao);

		return conta;
	}

	private Conta validarTransacao(TransacaoDTO dto) {
		if (dto.getValor() == null || dto.getValor() <= 0) {
			throw new TransacaoException("O valor da transação deve ser maior que zero");
		}

		String formaPagamento = dto.getFormaPagamento().toUpperCase();
		ITransacao transacaoService = transacaoMap.get(formaPagamento);

		Conta conta = contaRepository.findByNumeroConta(dto.getNumeroConta())
				.orElseThrow(() -> new ContaExeption("conta não encontrada"));

		if (transacaoService == null) {
			throw new TransacaoException(
					"Forma de pagamento inválida. Use P para Pix. Use D para débito e C para crédito");
		}

		return conta;
	}
}
