package com.desafio.ngbilling.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.ngbilling.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID>{

}
