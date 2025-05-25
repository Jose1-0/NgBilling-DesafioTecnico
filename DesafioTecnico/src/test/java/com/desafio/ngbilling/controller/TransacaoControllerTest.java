package com.desafio.ngbilling.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desafio.ngbilling.dto.TransacaoDTO;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.service.impl.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransacaoControllerTest {

	private MockMvc mockMvc;

	@Mock
	private TransacaoService transacaoService;

	@InjectMocks
	private TransacaoController transacaoController;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void realizarTransacao_deveRetornarCreated() throws Exception {
		TransacaoDTO dto = new TransacaoDTO("P", 123, 50f);
		Conta conta = new Conta(123, 150f);

		when(transacaoService.realizarTransacao(any(TransacaoDTO.class))).thenReturn(conta);

		mockMvc.perform(post("/transacao").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.numero_conta").value(123)).andExpect(jsonPath("$.saldo").value(150f));
	}
}
