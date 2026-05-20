package com.cdent.facturas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cdent.facturas.model.EstadoFactura;
import com.cdent.facturas.model.Factura;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FacturaRequestDTO {

    @NotNull(message = "El citaId es obligatorio")
    private Long citaId;

    @NotNull(message = "El pacienteId es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El monto total es obligatorio")
    private BigDecimal montoTotal;

    @NotNull(message = "El monto de impuesto es obligatorio")
    private BigDecimal montoImpuesto;

    @NotNull(message = "El estado es obligatorio")
    private EstadoFactura estado;

    @NotNull(message = "La fecha de emisión es obligatoria")
    private LocalDate fechaEmision;

    private LocalDate fechaVencimiento;

    public Factura toEntity() {
        return Factura.builder()
                .citaId(citaId)
                .pacienteId(pacienteId)
                .montoTotal(montoTotal)
                .montoImpuesto(montoImpuesto)
                .estado(estado)
                .fechaEmision(fechaEmision)
                .fechaVencimiento(fechaVencimiento)
                .build();
    }

    public void actualizarEntidad(Factura factura) {
        factura.setCitaId(citaId);
        factura.setPacienteId(pacienteId);
        factura.setMontoTotal(montoTotal);
        factura.setMontoImpuesto(montoImpuesto);
        factura.setEstado(estado);
        factura.setFechaEmision(fechaEmision);
        factura.setFechaVencimiento(fechaVencimiento);
    }
}
