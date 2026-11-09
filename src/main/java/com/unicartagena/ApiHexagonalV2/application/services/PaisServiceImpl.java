package com.unicartagena.apihexagonal.application.services;

import com.unicartagena.apihexagonal.application.ports.PaisService;
import com.unicartagena.apihexagonal.domain.model.Pais;
import com.unicartagena.apihexagonal.domain.ports.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del caso de uso.
 * Coordina la lógica de negocio usando los puertos.
 */
@Service
@RequiredArgsConstructor
public class PaisServiceImpl implements PaisService {

    private final PaisRepository paisRepository;

    @Override
    public Pais crear(Pais pais) {
        return paisRepository.save(pais);
    }

    @Override
    public Optional<Pais> buscarPorId(Long id) {
        return paisRepository.findById(id);
    }

    @Override
    public List<Pais> listarTodos() {
        return paisRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        paisRepository.deleteById(id);
    }

    @Override
    public Pais actualizar(Long id, Pais pais) {
        return paisRepository.findById(id)
                .map(paisExistente -> {
                    paisExistente.setNombre(pais.getNombre());
                    paisExistente.setPresidente(pais.getPresidente());
                    paisExistente.setContinente(pais.getContinente());
                    paisExistente.setNombreHimno(pais.getNombreHimno());
                    paisExistente.setNumHabitantes(pais.getNumHabitantes());
                    paisExistente.setNumDepartamentos(pais.getNumDepartamentos());
                    paisExistente.setNumMunicipios(pais.getNumMunicipios());
                    paisExistente.setIdiomaPpal(pais.getIdiomaPpal());
                    paisExistente.setNumUniversidades(pais.getNumUniversidades());
                    paisExistente.setTipoDemocracia(pais.getTipoDemocracia());
                    return paisRepository.save(paisExistente);
                })
                .orElseThrow(() -> new RuntimeException("País no encontrado con id: " + id));
    }

    @Override
    public List<Pais> filtrarPorContinente(String continente) {
        return paisRepository.findAll().stream()
                .filter(p -> p.getContinente() != null && p.getContinente().equalsIgnoreCase(continente))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<Pais> filtrarPorPoblacionMinima(Integer min) {
        return paisRepository.findAll().stream()
                .filter(p -> p.getNumHabitantes() != null && p.getNumHabitantes() >= min)
                .collect(java.util.stream.Collectors.toList());
    }
}