package com.cdent.odontologos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdent.odontologos.dto.OdontologoRequestDTO;
import com.cdent.odontologos.service.OdontologoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(odontologoService.findAll());
    }

    @GetMapping("/activos")
    public ResponseEntity<?> findActivos() {
        return ResponseEntity.ok(odontologoService.findActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OdontologoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody OdontologoRequestDTO request) {
        return ResponseEntity.ok(odontologoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        odontologoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
