package com.unicartagena.ApiHexagonalV2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

// AQUÍ ESTÁ LA CLAVE: le decimos explícitamente cuál es la clase de configuración
@SpringBootTest(classes = ApiHexagonalApplication.class)
@ActiveProfiles("test")
class ApiHexagonalApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true, "El contexto de Spring cargó correctamente");
    }
}