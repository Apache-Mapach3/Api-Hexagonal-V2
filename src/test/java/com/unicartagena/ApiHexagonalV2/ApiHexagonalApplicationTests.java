package com.unicartagena.ApiHexagonalV2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApiHexagonalApplicationTests {

    @Test
    void contextLoads() {
        // Si este método se ejecuta sin lanzar excepciones,
        // significa que el contexto de Spring cargó correctamente.
    }
}