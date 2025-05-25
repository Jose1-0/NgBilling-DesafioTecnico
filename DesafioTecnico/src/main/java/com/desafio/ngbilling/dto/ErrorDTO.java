package com.desafio.ngbilling.dto;

public class ErrorDTO {

	private String erro;

	public ErrorDTO(String erro) {
		this.erro = erro;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
}
