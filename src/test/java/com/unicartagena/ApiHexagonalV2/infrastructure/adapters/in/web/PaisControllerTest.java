package com.unicartagena.ApiHexagonalV2V2.infrastructure.adapters.in.web;

import com.unicartagena.ApiHexagonalV2V2.application.ports.PaisService;
import com.unicartagena.ApiHexagonalV2V2.domain.model.Pais;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaisController.class)
class PaisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaisService paisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listar_DeberiaRetornarListaDePaisesYStatus200() throws Exception {
        List<Pais> paises = Arrays.asList(
                Pais.builder().id(1L).nombre("Colombia").build(),
                Pais.builder().id(2L).nombre("México").build()
        );
        when(paisService.listarTodos()).thenReturn(paises);

        mockMvc.perform(get("/api/v1/paises")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Colombia"));
    }
}