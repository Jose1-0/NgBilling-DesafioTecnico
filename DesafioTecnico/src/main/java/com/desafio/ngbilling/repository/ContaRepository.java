package com.desafio.ngbilling.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.ngbilling.model.Conta;

public interface ContaRepository  extends JpaRepository<Conta, UUID>{
	
	Optional<Conta> findByNumeroConta(Integer numeroConta);
}
