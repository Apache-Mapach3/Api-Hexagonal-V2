package com.unicartagena.ApiHexagonalV2V2.infrastructure.adapters.out.persistence.repository;

import com.unicartagena.ApiHexagonalV2V2.domain.model.Usuario;
import com.unicartagena.ApiHexagonalV2V2.domain.ports.UsuarioRepository;
import com.unicartagena.ApiHexagonalV2V2.infrastructure.adapters.out.persistence.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

// Interfaz de Spring Data JPA interna
interface UsuarioSpringDataRepository extends org.springframework.data.jpa.repository.JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);
    Optional<UsuarioEntity> findByTokenRecuperacion(String token);
}

@Component
@RequiredArgsConstructor
public class UsuarioJpaAdapter implements UsuarioRepository {
    private final UsuarioSpringDataRepository repository;

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setEmail(usuario.getEmail());
        entity.setPassword(usuario.getPassword());
        entity.setTokenRecuperacion(usuario.getTokenRecuperacion());
        entity.setFechaExpiracionToken(usuario.getFechaExpiracionToken());

        UsuarioEntity saved = repository.save(entity);
        return toModel(saved);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email).map(this::toModel);
    }

    @Override
    public Optional<Usuario> findByTokenRecuperacion(String token) {
        return repository.findByTokenRecuperacion(token).map(this::toModel);
    }

    private Usuario toModel(UsuarioEntity e) {
        return Usuario.builder()
                .id(e.getId()).nombre(e.getNombre()).email(e.getEmail())
                .password(e.getPassword()).tokenRecuperacion(e.getTokenRecuperacion())
                .fechaExpiracionToken(e.getFechaExpiracionToken()).build();
    }
}