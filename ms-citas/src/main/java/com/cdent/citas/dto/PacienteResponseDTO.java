package com.cdent.citas.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * Réplica del DTO de pacientes para consumo vía OpenFeign.
 */
@Data
public class PacienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String direccion;
    private LocalDateTime fechaCreacion;
}
