package com.desafio.ngbilling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaDTO {

	@JsonProperty(index = 1, value = "numero_conta")
	private Integer numeroConta;

	@JsonProperty(index = 2)
	private float saldo;

	public ContaDTO() {
	}

	public ContaDTO(Integer numeroConta, float saldo) {
		this.numeroConta = numeroConta;
		this.saldo = (float) (Math.round(saldo * 100.0) / 100.0);
	}

	public Integer getNumero_conta() {
		return numeroConta;
	}

	public void setNumero_conta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

}
