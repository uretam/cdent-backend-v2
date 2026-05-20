package com.cdent.facturas.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * Réplica del DTO de citas para validación remota vía Feign.
 */
@Data
public class CitaResponseDTO {

    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private LocalDateTime fechaCita;
    private String estado;
    private String notas;
    private LocalDateTime fechaCreacion;
    private List<DetalleCitaResponseDTO> detalles;

    @Data
    public static class DetalleCitaResponseDTO {
        private Long id;
        private Long tratamientoId;
        private Integer cantidad;
        private java.math.BigDecimal precioUnitario;
        private java.math.BigDecimal subtotal;
        private String notas;
    }
}
