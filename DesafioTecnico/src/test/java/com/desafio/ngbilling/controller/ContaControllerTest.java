package com.desafio.ngbilling.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.desafio.ngbilling.dto.ContaDTO;
import com.desafio.ngbilling.model.Conta;
import com.desafio.ngbilling.service.impl.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private ContaController contaController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contaController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void criarContaDeveRetornarCreated() throws Exception {
        ContaDTO contaDTO = new ContaDTO(123, 100f);
        Conta conta = new Conta(123, 100f);

        when(contaService.criarConta(any(Conta.class))).thenReturn(conta);

        mockMvc.perform(post("/conta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numero_conta").value(123))
                .andExpect(jsonPath("$.saldo").value(100f));
    }

    @Test
    void buscarContaPorNumeroDeveRetornarOk() throws Exception {
        Integer numeroConta = 123;
        Conta conta = new Conta(numeroConta, 200f);

        when(contaService.buscarConta(numeroConta)).thenReturn(conta);

        mockMvc.perform(get("/conta")
                .param("numeroConta", numeroConta.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero_conta").value(123))
                .andExpect(jsonPath("$.saldo").value(200f));
    }
}
