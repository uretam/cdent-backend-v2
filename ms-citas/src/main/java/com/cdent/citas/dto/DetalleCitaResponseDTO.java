package com.cdent.citas.dto;

import java.math.BigDecimal;

import com.cdent.citas.model.DetalleCita;

import lombok.Data;

/**
 * DTO de salida para detalle de cita.
 */
@Data
public class DetalleCitaResponseDTO {

    private Long id;
    private Long tratamientoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String notas;

    public static DetalleCitaResponseDTO fromEntity(DetalleCita detalle) {
        DetalleCitaResponseDTO dto = new DetalleCitaResponseDTO();
        dto.setId(detalle.getId());
        dto.setTratamientoId(detalle.getTratamientoId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setSubtotal(detalle.getSubtotal());
        dto.setNotas(detalle.getNotas());
        return dto;
    }
}
