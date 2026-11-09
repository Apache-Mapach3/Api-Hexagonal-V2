package com.unicartagena.ApiHexagonalV2.application.services;

import com.unicartagena.ApiHexagonalV2.application.ports.AuthService;
import com.unicartagena.ApiHexagonalV2.domain.model.Usuario;
import com.unicartagena.ApiHexagonalV2.domain.ports.UsuarioRepository;
import com.unicartagena.ApiHexagonalV2.infrastructure.utils.EmailService;
import com.unicartagena.ApiHexagonalV2.infrastructure.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    @Value("${app.frontend.url}")
    private String appFrontendUrl;

    @Override
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public String login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return jwtUtil.generarToken(usuario.getEmail());
    }

    @Override
    public void solicitarRecuperacionPassword(String email) {
        // No lanzamos error si el email no existe (evita enumeración de usuarios)
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String token = UUID.randomUUID().toString();
            usuario.setTokenRecuperacion(token);
            usuario.setFechaExpiracionToken(LocalDateTime.now().plusHours(1));
            usuarioRepository.save(usuario);

            String link = appFrontendUrl + "/reset-password?token=" + token;
            emailService.enviarCorreoRecuperacion(usuario.getEmail(), link);
        }
    }

    @Override
    public void restablecerPassword(String token, String nuevoPassword) {
        Usuario usuario = usuarioRepository.findByTokenRecuperacion(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (usuario.getFechaExpiracionToken().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El token ha expirado");
        }

        usuario.setPassword(passwordEncoder.encode(nuevoPassword));
        usuario.setTokenRecuperacion(null);
        usuario.setFechaExpiracionToken(null);
        usuarioRepository.save(usuario);
    }
}