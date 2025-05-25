package com.desafio.ngbilling.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.desafio.ngbilling.dto.ErrorDTO;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleContaException_naoEncontrada() {
        ContaExeption ex = new ContaExeption("conta não encontrada");
        ResponseEntity<Object> response = handler.handleContaException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(((ErrorDTO) response.getBody()).getErro().contains("conta não encontrada"));
    }

    @Test
    void handleContaException_negativo() {
        ContaExeption ex = new ContaExeption("O saldo Inicial não pode ser negativo.");
        ResponseEntity<Object> response = handler.handleContaException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(((ErrorDTO) response.getBody()).getErro().contains("negativo"));
    }

    @Test
    void handleContaException_existe() {
        ContaExeption ex = new ContaExeption("Já existe uma conta com esse número.");
        ResponseEntity<Object> response = handler.handleContaException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(((ErrorDTO) response.getBody()).getErro().contains("existe"));
    }

    @Test
    void handleContaException_numeroContaInvalido() {
        ContaExeption ex = new ContaExeption("Número de conta inválido");
        ResponseEntity<Object> response = handler.handleContaException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(((ErrorDTO) response.getBody()).getErro().contains("inválido"));
    }

    @Test
    void handleContaException_outroErro() {
        ContaExeption ex = new ContaExeption("Erro desconhecido");
        ResponseEntity<Object> response = handler.handleContaException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(((ErrorDTO) response.getBody()).getErro().contains("Erro desconhecido"));
    }

    @Test
    void handleTransacaoException() {
        TransacaoException ex = new TransacaoException("transação não permitida");
        ResponseEntity<Object> response = handler.handleTransacaoException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(((ErrorDTO) response.getBody()).getErro().contains("transação não permitida"));
    }
}
