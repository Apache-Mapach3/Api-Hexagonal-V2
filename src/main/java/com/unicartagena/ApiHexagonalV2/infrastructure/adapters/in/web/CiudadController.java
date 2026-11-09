package com.unicartagena.apihexagonal.infrastructure.adapters.in.web;

import com.unicartagena.apihexagonal.application.ports.CiudadService;
import com.unicartagena.apihexagonal.domain.model.Ciudad;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ciudades")
@RequiredArgsConstructor
@Tag(name = "Ciudades", description = "API para gestión de ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    @PostMapping
    @Operation(summary = "Crear una nueva ciudad")
    public ResponseEntity<Ciudad> crear(@RequestBody Ciudad ciudad) {
        return ResponseEntity.ok(ciudadService.crear(ciudad));
    }

    @GetMapping
    @Operation(summary = "Listar todas las ciudades")
    public ResponseEntity<List<Ciudad>> listar() {
        return ResponseEntity.ok(ciudadService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar ciudad por ID")
    public ResponseEntity<Ciudad> buscarPorId(@PathVariable Long id) {
        return ciudadService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una ciudad existente")
    public ResponseEntity<Ciudad> actualizar(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        return ResponseEntity.ok(ciudadService.actualizar(id, ciudad));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ciudad por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ciudadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar ciudades por clima o país")
    public ResponseEntity<List<Ciudad>> buscar(
            @RequestParam(required = false) String clima,
            @RequestParam(required = false) String pais) {
        if (clima != null) return ResponseEntity.ok(ciudadService.buscarPorClima(clima));
        if (pais != null) return ResponseEntity.ok(ciudadService.buscarPorPais(pais));
        return ResponseEntity.badRequest().build();
    }
}