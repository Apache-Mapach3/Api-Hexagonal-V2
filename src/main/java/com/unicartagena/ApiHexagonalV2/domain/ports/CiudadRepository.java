package com.unicartagena.apihexagonal.domain.ports;

import com.unicartagena.apihexagonal.domain.model.Ciudad;
import java.util.List;
import java.util.Optional;

public interface CiudadRepository {
    Ciudad save(Ciudad ciudad);
    Optional<Ciudad> findById(Long id);
    List<Ciudad> findAll();
    void deleteById(Long id);
    List<Ciudad> findByClima(String clima);
    List<Ciudad> findByPaisPertenece(String pais);
}