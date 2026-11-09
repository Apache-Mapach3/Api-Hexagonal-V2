package com.unicartagena.ApiHexagonalV2V2.infrastructure.adapters.in.web;

import com.unicartagena.ApiHexagonalV2V2.application.ports.AuthService;
import com.unicartagena.ApiHexagonalV2V2.domain.model.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints de Login y Recuperación")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        authService.registrar(usuario);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String token = authService.login(credenciales.get("email"), credenciales.get("password"));
        return ResponseEntity.ok(Map.of("token", token, "mensaje", "Login exitoso"));
    }

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperar(@RequestBody Map<String, String> request) {
        authService.solicitarRecuperacionPassword(request.get("email"));
        return ResponseEntity.ok(Map.of("mensaje", "Correo de recuperación enviado"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetear(@RequestBody Map<String, String> request) {
        authService.restablecerPassword(request.get("token"), request.get("nuevoPassword"));
        return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada exitosamente"));
    }
}