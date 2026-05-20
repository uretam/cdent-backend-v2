package com.cdent.tratamientos.controller;

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

import com.cdent.tratamientos.dto.TratamientoRequestDTO;
import com.cdent.tratamientos.service.TratamientoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {

    private final TratamientoService tratamientoService;

    public TratamientoController(TratamientoService tratamientoService) {
        this.tratamientoService = tratamientoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(tratamientoService.findAll());
    }

    @GetMapping("/activos")
    public ResponseEntity<?> findActivos() {
        return ResponseEntity.ok(tratamientoService.findActivos());
    }

    @GetMapping("/categoria")
    public ResponseEntity<?> findByCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(tratamientoService.findByCategoria(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tratamientoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TratamientoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamientoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TratamientoRequestDTO request) {
        return ResponseEntity.ok(tratamientoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tratamientoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
