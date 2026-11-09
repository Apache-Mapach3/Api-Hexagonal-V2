package com.unicartagena.ApiHexagonalV2.application.services;

import com.unicartagena.ApiHexagonalV2.application.ports.CiudadService;
import com.unicartagena.ApiHexagonalV2.domain.model.Ciudad;
import com.unicartagena.ApiHexagonalV2.domain.ports.CiudadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CiudadServiceImpl implements CiudadService {

    private final CiudadRepository ciudadRepository;

    @Override
    public Ciudad crear(Ciudad ciudad) { return ciudadRepository.save(ciudad); }

    @Override
    public Optional<Ciudad> buscarPorId(Long id) { return ciudadRepository.findById(id); }

    @Override
    public List<Ciudad> listarTodos() { return ciudadRepository.findAll(); }

    @Override
    public void eliminar(Long id) { ciudadRepository.deleteById(id); }

    @Override
    public Ciudad actualizar(Long id, Ciudad ciudad) {
        return ciudadRepository.findById(id)
                .map(c -> {
                    c.setNombre(ciudad.getNombre());
                    c.setPaisPertenece(ciudad.getPaisPertenece());
                    c.setAlcalde(ciudad.getAlcalde());
                    c.setClima(ciudad.getClima());
                    return ciudadRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
    }

    @Override
    public List<Ciudad> buscarPorClima(String clima) { return ciudadRepository.findByClima(clima); }

    @Override
    public List<Ciudad> buscarPorPais(String pais) { return ciudadRepository.findByPaisPertenece(pais); }
}