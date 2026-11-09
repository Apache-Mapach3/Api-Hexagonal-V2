package com.unicartagena.apihexagonal.infrastructure.adapters.out.persistence;

import com.unicartagena.apihexagonal.domain.model.Pais;
import com.unicartagena.apihexagonal.domain.ports.PaisRepository;
import com.unicartagena.apihexagonal.infrastructure.adapters.out.persistence.entity.PaisEntity;
import com.unicartagena.apihexagonal.infrastructure.adapters.out.persistence.repository.JpaPaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de salida - Implementa el puerto del dominio usando JPA.
 */
@Component
@RequiredArgsConstructor
public class PaisRepositoryImpl implements PaisRepository {

    private final JpaPaisRepository jpaRepository;

    @Override
    public Pais save(Pais pais) {
        PaisEntity entity = toEntity(pais);
        return toModel(jpaRepository.save(entity));
    }

    @Override
    public Optional<Pais> findById(Long id) {
        return jpaRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<Pais> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    // ============ MAPPERS ============
    private Pais toModel(PaisEntity e) {
        return Pais.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .presidente(e.getPresidente())
                .continente(e.getContinente())
                .nombreHimno(e.getNombreHimno())
                .numHabitantes(e.getNumHabitantes())
                .numDepartamentos(e.getNumDepartamentos())
                .numMunicipios(e.getNumMunicipios())
                .idiomaPpal(e.getIdiomaPpal())
                .numUniversidades(e.getNumUniversidades())
                .tipoDemocracia(e.getTipoDemocracia())
                .build();
    }

    private PaisEntity toEntity(Pais p) {
        PaisEntity e = new PaisEntity();
        e.setId(p.getId());
        e.setNombre(p.getNombre());
        e.setPresidente(p.getPresidente());
        e.setContinente(p.getContinente());
        e.setNombreHimno(p.getNombreHimno());
        e.setNumHabitantes(p.getNumHabitantes());
        e.setNumDepartamentos(p.getNumDepartamentos());
        e.setNumMunicipios(p.getNumMunicipios());
        e.setIdiomaPpal(p.getIdiomaPpal());
        e.setNumUniversidades(p.getNumUniversidades());
        e.setTipoDemocracia(p.getTipoDemocracia());
        return e;
    }
}