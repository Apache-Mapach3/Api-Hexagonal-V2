package com.unicartagena.apihexagonal.domain.ports;

import com.unicartagena.apihexagonal.domain.model.Pais;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida - Define el contrato de persistencia.
 * El dominio NO conoce cómo se implementa.
 */
public interface PaisRepository {
    Pais save(Pais pais);
    Optional<Pais> findById(Long id);
    List<Pais> findAll();
    void deleteById(Long id);
}