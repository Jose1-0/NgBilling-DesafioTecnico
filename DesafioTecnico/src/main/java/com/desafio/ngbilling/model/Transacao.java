package com.desafio.ngbilling.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacoes")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(optional = false)
	private Conta conta;
	
	@Column(name = "forma_pagamento", nullable = false)
	private String formaPagamento;
	
	@Column(nullable = false)
	private float valor;

	public Transacao(Conta conta, String formaPagamento, float valor) {

		this.conta = conta;
		this.formaPagamento = formaPagamento;
		this.valor = valor;
	}

	public Transacao() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Transacao [conta=" + conta + ", formaPagamento=" + formaPagamento + ", valor=" + valor + "]";
	}
	
}
