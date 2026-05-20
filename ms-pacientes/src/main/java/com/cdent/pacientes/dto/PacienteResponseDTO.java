package com.cdent.pacientes.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cdent.pacientes.model.Paciente;

import lombok.Data;

/**
 * DTO de salida con los datos del paciente expuestos al cliente REST.
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

    /**
     * Mapeo estático desde la entidad sin MapStruct.
     */
    public static PacienteResponseDTO fromEntity(Paciente paciente) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setCorreo(paciente.getCorreo());
        dto.setTelefono(paciente.getTelefono());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());
        dto.setDireccion(paciente.getDireccion());
        dto.setFechaCreacion(paciente.getFechaCreacion());
        return dto;
    }
}
