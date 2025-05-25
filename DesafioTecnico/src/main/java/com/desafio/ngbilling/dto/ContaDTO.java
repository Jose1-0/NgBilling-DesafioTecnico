package com.desafio.ngbilling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaDTO {

	@JsonProperty(index = 1, value = "numero_conta")
	private Integer numeroConta;

	@JsonProperty(index = 2)
	private Float saldo;

	public ContaDTO() {
	}

	public ContaDTO(Integer numeroConta, Float saldo) {
		this.numeroConta = numeroConta;
		this.saldo = (float) (Math.round(saldo * 100.0) / 100.0);
	}

	public Integer getNumero_conta() {
		return numeroConta;
	}

	public void setNumero_conta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Float getSaldo() {
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

}
