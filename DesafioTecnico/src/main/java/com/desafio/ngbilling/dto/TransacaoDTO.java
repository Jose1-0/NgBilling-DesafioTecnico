package com.desafio.ngbilling.dto;

public class TransacaoDTO {

	private String formaPagamento;
	private Integer numeroConta;
	private Float valor;

	public TransacaoDTO() {

	}

	public TransacaoDTO(String formaPagamento, Integer numeroConta, float valor) {
		this.formaPagamento = formaPagamento;
		this.numeroConta = numeroConta;
		this.valor = valor;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
