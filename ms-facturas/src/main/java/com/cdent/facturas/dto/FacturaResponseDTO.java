package com.cdent.facturas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cdent.facturas.model.EstadoFactura;
import com.cdent.facturas.model.Factura;

import lombok.Data;

@Data
public class FacturaResponseDTO {

    private Long id;
    private Long citaId;
    private Long pacienteId;
    private BigDecimal montoTotal;
    private BigDecimal montoImpuesto;
    private EstadoFactura estado;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private LocalDateTime fechaCreacion;

    public static FacturaResponseDTO fromEntity(Factura factura) {
        FacturaResponseDTO dto = new FacturaResponseDTO();
        dto.setId(factura.getId());
        dto.setCitaId(factura.getCitaId());
        dto.setPacienteId(factura.getPacienteId());
        dto.setMontoTotal(factura.getMontoTotal());
        dto.setMontoImpuesto(factura.getMontoImpuesto());
        dto.setEstado(factura.getEstado());
        dto.setFechaEmision(factura.getFechaEmision());
        dto.setFechaVencimiento(factura.getFechaVencimiento());
        dto.setFechaCreacion(factura.getFechaCreacion());
        return dto;
    }
}
