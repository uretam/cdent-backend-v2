package com.cdent.pacientes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdent.pacientes.dto.PacienteRequestDTO;
import com.cdent.pacientes.dto.PacienteResponseDTO;
import com.cdent.pacientes.service.PacienteService;

import jakarta.validation.Valid;

/**
 * Controlador REST que expone el CRUD de pacientes bajo /api/pacientes.
 */
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<PacienteResponseDTO> pacientes = pacienteService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PacienteResponseDTO paciente = pacienteService.findById(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> findByApellido(@RequestParam String apellido) {
        List<PacienteResponseDTO> pacientes = pacienteService.findByApellido(apellido);
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PacienteRequestDTO request) {
        PacienteResponseDTO creado = pacienteService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PacienteRequestDTO request) {
        PacienteResponseDTO actualizado = pacienteService.update(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
