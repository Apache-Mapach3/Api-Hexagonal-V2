package com.unicartagena.apihexagonal.infrastructure.adapters.in.web;

import com.unicartagena.apihexagonal.application.ports.PaisService;
import com.unicartagena.apihexagonal.domain.model.Pais;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Adaptador de entrada - Controlador REST.
 * Expone los endpoints HTTP para consumir los casos de uso.
 */
@RestController
@RequestMapping("/api/v1/paises")
@RequiredArgsConstructor
@Tag(name = "Paises", description = "API para gestión de países")
public class PaisController {

    private final PaisService paisService;

    @PostMapping
    @Operation(summary = "Crear un nuevo país")
    @ApiResponse(responseCode = "200", description = "País creado exitosamente")
    public ResponseEntity<Pais> crear(@RequestBody Pais pais) {
        return ResponseEntity.ok(paisService.crear(pais));
    }

    @GetMapping
    @Operation(summary = "Listar todos los países")
    public ResponseEntity<List<Pais>> listar() {
        return ResponseEntity.ok(paisService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar país por ID")
    public ResponseEntity<Pais> buscarPorId(@PathVariable Long id) {
        return paisService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar país por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        paisService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un país existente")
    public ResponseEntity<Pais> actualizar(@PathVariable Long id, @RequestBody Pais pais) {
        return ResponseEntity.ok(paisService.actualizar(id, pais));
    }
}