package com.desafio.ngbilling.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.desafio.ngbilling.exception.ContaExeption;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.repository.ContaRepository;
import com.desafio.ngbilling.service.impl.ContaService;

public class ContaServiceTest {

	@Mock
	ContaRepository contaRepository;
	
	@InjectMocks
	private ContaService contaService;
	
	@BeforeEach
	void  setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
    @Test
    void criarContaComSaldoNegativoDeveLancarExcecao() {
        
    	Conta conta = new Conta(1, -10f);      
        assertThrows(ContaExeption.class, () -> contaService.criarConta(conta));
    }

    @Test
    void criarContaComContaExistenteDeveLancarExcecao() {
    	
        Conta conta = new Conta(1, 10f);
        when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.of(conta));
        assertThrows(ContaExeption.class, () -> contaService.criarConta(conta));
    }

    @Test
    void criarContaComDadosValidosDeveSalvarConta() {
    	
        Conta conta = new Conta(1, 100f);
        when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.empty());
        when(contaRepository.save(conta)).thenReturn(conta);
        assertDoesNotThrow(()-> contaService.criarConta(conta));
    }

    @Test
    void buscarContaComContaExistenteDeveRetornarConta() {
    	
        Conta conta = new Conta(1, 50f);
        when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.of(conta));        
        assertDoesNotThrow(()-> contaService.buscarConta(1));

    }

    @Test
    void buscarConta_comContaInexistente_deveLancarExcecao() {
    	
        when(contaRepository.findByNumeroConta(1)).thenReturn(Optional.empty());
       assertThrows(ContaExeption.class, () -> contaService.buscarConta(1));
    }
    
    @Test
    void criarContaComNumeroContaInvalidoDeveLancarExcecao() {
        Conta conta = new Conta(-1, 100f);
        assertThrows(ContaExeption.class, () -> contaService.criarConta(conta));
    }
    
    @Test
    void toStringDeveRetornarStringFormatadaCorretamente() {
        Conta conta = new Conta();
        conta.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        conta.setNumeroConta(123);
        conta.setSaldo(100.0f);

        String esperado = "Conta [id=123e4567-e89b-12d3-a456-426614174000, numeroConta=123, saldo=100.0]";
        assertEquals(esperado, conta.toString());
    }

}
