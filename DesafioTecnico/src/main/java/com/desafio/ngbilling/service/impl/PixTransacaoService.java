package com.desafio.ngbilling.service.impl;

import org.springframework.stereotype.Service;

import com.desafio.ngbilling.exception.TransacaoException;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.service.ITransacao;

@Service("P")
public class PixTransacaoService implements ITransacao {

	@Override
	public void transacao(Conta conta, float valor) {
		if (conta.getSaldo() < valor) {
			throw new TransacaoException("Saldo insuficiente para transação Pix");
		}
		
		conta.setSaldo(conta.getSaldo() - valor);
	}
}
