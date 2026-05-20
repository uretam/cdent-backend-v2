package com.cdent.facturas.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.cdent.facturas.dto.FacturaRequestDTO;
import com.cdent.facturas.dto.FacturaResponseDTO;

public interface FacturaService {

    List<FacturaResponseDTO> findAll();

    FacturaResponseDTO findById(Long id);

    FacturaResponseDTO create(FacturaRequestDTO request);

    FacturaResponseDTO update(Long id, FacturaRequestDTO request);

    void delete(Long id);

    BigDecimal getTotalFacturadoPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin);

    List<FacturaResponseDTO> findFacturasPendientesPorPaciente(Long pacienteId);
}
