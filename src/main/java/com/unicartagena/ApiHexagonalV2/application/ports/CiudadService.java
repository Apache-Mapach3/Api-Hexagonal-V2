package com.unicartagena.apihexagonal.application.ports;

import com.unicartagena.apihexagonal.domain.model.Ciudad;
import java.util.List;
import java.util.Optional;

public interface CiudadService {
    Ciudad crear(Ciudad ciudad);
    Optional<Ciudad> buscarPorId(Long id);
    List<Ciudad> listarTodos();
    void eliminar(Long id);
    Ciudad actualizar(Long id, Ciudad ciudad);
    List<Ciudad> buscarPorClima(String clima);
    List<Ciudad> buscarPorPais(String pais);
}