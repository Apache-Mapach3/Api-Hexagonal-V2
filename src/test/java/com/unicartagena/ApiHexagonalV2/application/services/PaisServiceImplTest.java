package com.unicartagena.ApiHexagonalV2.application.services;

import com.unicartagena.ApiHexagonalV2.domain.model.Pais;
import com.unicartagena.ApiHexagonalV2.domain.ports.PaisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaisServiceImplTest {

    @Mock
    private PaisRepository paisRepository;

    @InjectMocks
    private PaisServiceImpl paisService;

    private Pais paisPrueba;

    @BeforeEach
    void setUp() {
        paisPrueba = Pais.builder()
                .id(1L)
                .nombre("Colombia")
                .continente("América")
                .numHabitantes(50000000)
                .build();
    }

    @Test
    void crear_DeberiaGuardarYRetornarPais() {
        when(paisRepository.save(paisPrueba)).thenReturn(paisPrueba);

        Pais resultado = paisService.crear(paisPrueba);

        assertNotNull(resultado);
        assertEquals("Colombia", resultado.getNombre());
        verify(paisRepository, times(1)).save(paisPrueba);
    }

    @Test
    void filtrarPorContinente_DeberiaRetornarListaFiltrada() {
        Pais pais2 = Pais.builder().id(2L).nombre("Argentina").continente("América").numHabitantes(45000000).build();
        Pais pais3 = Pais.builder().id(3L).nombre("España").continente("Europa").numHabitantes(47000000).build();

        when(paisRepository.findAll()).thenReturn(Arrays.asList(paisPrueba, pais2, pais3));

        List<Pais> resultado = paisService.filtrarPorContinente("América");

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(p -> "América".equalsIgnoreCase(p.getContinente())));
    }
}