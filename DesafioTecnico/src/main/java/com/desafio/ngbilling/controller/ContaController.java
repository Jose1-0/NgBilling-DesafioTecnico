package com.desafio.ngbilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.ngbilling.dto.ContaDTO;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.service.impl.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@PostMapping
	public ResponseEntity<ContaDTO> criarConta(@RequestBody ContaDTO contaDTO){
		
		Conta conta = new Conta(contaDTO.getNumero_conta(), contaDTO.getSaldo());
		Conta novaConta = contaService.criarConta(conta);
		
		return ResponseEntity.status(201).body(new ContaDTO(novaConta.getNumeroConta(), novaConta.getSaldo()));
		
	}
	
	@GetMapping
	public ResponseEntity<ContaDTO> buscarContasPorNumero(@RequestParam("numeroConta") Integer numeroConta) {
	    Conta conta = contaService.buscarConta(numeroConta);
	    return ResponseEntity.ok(new ContaDTO(conta.getNumeroConta(), conta.getSaldo()));
	}
}
