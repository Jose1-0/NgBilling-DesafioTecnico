package com.desafio.ngbilling.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.ngbilling.exception.ContaExeption;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	public Conta criarConta(Conta conta) {

		if (conta.getSaldo() < 0) {
			throw new ContaExeption("O saldo Inicial não pode ser negativo.");
		}

		Optional<Conta> contaExistente = contaRepository.findByNumeroConta(conta.getNumeroConta());
		if (contaExistente.isPresent()) {
			
			throw new ContaExeption("Já existe uma conta com esse número.");
		}

		return contaRepository.save(conta);
	}
	
	public Conta buscarConta(Integer numeroConta) {
	    return contaRepository.findByNumeroConta(numeroConta)
	            .orElseThrow(() -> new ContaExeption("Conta não encontrada com o número. Verifique o numero de conta digitao"));
	}
}
