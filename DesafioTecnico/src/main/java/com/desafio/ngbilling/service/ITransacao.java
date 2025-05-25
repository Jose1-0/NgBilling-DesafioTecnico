package com.desafio.ngbilling.service;

import com.desafio.ngbilling.model.Conta;

public interface ITransacao {

	public void transacao(Conta conta, float valor);
}
