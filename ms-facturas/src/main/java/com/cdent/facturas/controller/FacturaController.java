package com.cdent.facturas.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.cdent.facturas.dto.FacturaRequestDTO;
import com.cdent.facturas.service.FacturaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(facturaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.findById(id));
    }

    @GetMapping("/total-facturado")
    public ResponseEntity<?> getTotalFacturadoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        BigDecimal total = facturaService.getTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/pendientes/paciente/{pacienteId}")
    public ResponseEntity<?> findFacturasPendientesPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(facturaService.findFacturasPendientesPorPaciente(pacienteId));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody FacturaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody FacturaRequestDTO request) {
        return ResponseEntity.ok(facturaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
