package com.unicartagena.apihexagonal.infrastructure.adapters.in.web;

import com.unicartagena.apihexagonal.application.ports.CiudadService;
import com.unicartagena.apihexagonal.application.ports.PaisService;
import com.unicartagena.apihexagonal.domain.model.Ciudad;
import com.unicartagena.apihexagonal.domain.model.Pais;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "API para reportes parametrizados")
public class ReporteController {

    private final PaisService paisService;
    private final CiudadService ciudadService;

    @GetMapping("/paises-por-continente")
    @Operation(summary = "Obtener países por continente")
    public ResponseEntity<List<Pais>> getPaisesPorContinente(@RequestParam String continente) {
        return ResponseEntity.ok(paisService.filtrarPorContinente(continente));
    }

    @GetMapping("/paises-poblacion-mayor")
    @Operation(summary = "Obtener países con población mayor a")
    public ResponseEntity<List<Pais>> getPaisesPorPoblacion(@RequestParam Integer min) {
        return ResponseEntity.ok(paisService.filtrarPorPoblacionMinima(min));
    }

    @GetMapping("/ciudades-por-clima")
    @Operation(summary = "Obtener ciudades por clima")
    public ResponseEntity<List<Ciudad>> getCiudadesPorClima(@RequestParam String clima) {
        return ResponseEntity.ok(ciudadService.buscarPorClima(clima));
    }

    @GetMapping("/ciudades-por-pais")
    @Operation(summary = "Obtener ciudades por país")
    public ResponseEntity<List<Ciudad>> getCiudadesPorPais(@RequestParam String pais) {
        return ResponseEntity.ok(ciudadService.buscarPorPais(pais));
    }
}