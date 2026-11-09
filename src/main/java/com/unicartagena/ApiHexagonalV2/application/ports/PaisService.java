package com.unicartagena.ApiHexagonalV2V2.application.ports;

import com.unicartagena.ApiHexagonalV2V2.domain.model.Pais;
import java.util.List;
import java.util.Optional;

public interface PaisService {
    Pais crear(Pais pais);
    Optional<Pais> buscarPorId(Long id);
    List<Pais> listarTodos();
    void eliminar(Long id);

    // ESTOS 3 MÉTODOS DEBEN ESTAR AQUÍ:
    Pais actualizar(Long id, Pais pais);
    List<Pais> filtrarPorContinente(String continente);
    List<Pais> filtrarPorPoblacionMinima(Integer min);
}