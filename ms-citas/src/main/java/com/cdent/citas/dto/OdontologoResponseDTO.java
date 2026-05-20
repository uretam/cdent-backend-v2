package com.cdent.citas.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * Réplica del DTO de odontólogos para consumo vía OpenFeign.
 */
@Data
public class OdontologoResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String especialidad;
    private String numeroLicencia;
    private String telefono;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
}
