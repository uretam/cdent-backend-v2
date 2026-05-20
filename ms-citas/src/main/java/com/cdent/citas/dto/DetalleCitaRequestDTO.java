package com.cdent.citas.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO de entrada para un detalle de cita.
 */
@Data
public class DetalleCitaRequestDTO {

    @NotNull(message = "El tratamientoId es obligatorio")
    private Long tratamientoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    private BigDecimal precioUnitario;

    private String notas;
}
