package com.desafio.ngbilling.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.ngbilling.dto.ContaDTO;
import com.desafio.ngbilling.exception.ContaExeption;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	public Conta criarConta(ContaDTO contaDTO) {
		Conta conta = validarConta(contaDTO);
		return contaRepository.save(conta);
	}

	public Conta buscarConta(Integer numeroConta) {
		return contaRepository.findByNumeroConta(numeroConta)
				.orElseThrow(() -> new ContaExeption("Conta não encontrada. Verifique o numero de conta digitao"));
	}

	private Conta validarConta(ContaDTO contaDTO) {
		Conta conta = new Conta(contaDTO.getNumero_conta(), contaDTO.getSaldo());

		if (conta.getNumeroConta() == null || conta.getNumeroConta() < 0) {
			throw new ContaExeption("Número de conta inválido");
		}

		if (conta.getSaldo() == null || conta.getSaldo() < 0) {
			throw new ContaExeption("O saldo Inicial não pode ser negativo.");
		}

		Optional<Conta> contaExistente = contaRepository.findByNumeroConta(conta.getNumeroConta());
		if (contaExistente.isPresent()) {
			throw new ContaExeption("Já existe uma conta com esse número.");
		}

		return conta;
	}
}
