package com.unicartagena.ApiHexagonalV2.application.ports;

import com.unicartagena.ApiHexagonalV2.domain.model.Usuario;

public interface AuthService {
    Usuario registrar(Usuario usuario);
    String login(String email, String password);
    void solicitarRecuperacionPassword(String email);
    void restablecerPassword(String token, String nuevoPassword);
}