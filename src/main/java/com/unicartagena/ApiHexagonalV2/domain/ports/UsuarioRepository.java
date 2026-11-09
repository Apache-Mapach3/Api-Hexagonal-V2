package com.unicartagena.ApiHexagonalV2V2.domain.ports;

import com.unicartagena.ApiHexagonalV2V2.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByTokenRecuperacion(String token);
}