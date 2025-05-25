package com.desafio.ngbilling.exception;

public class TransacaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1172080771596363706L;

	public TransacaoException(String mensagem) {
		super(mensagem);
	}
}
