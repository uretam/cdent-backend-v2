package com.cdent.pacientes.dto;

import java.time.LocalDate;

import com.cdent.pacientes.model.Paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO de entrada para crear o actualizar pacientes con validaciones Bean Validation.
 */
@Data
public class PacienteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El correo debe tener formato válido")
    private String correo;

    private String telefono;

    private LocalDate fechaNacimiento;

    private String direccion;

    /**
     * Convierte el DTO en entidad JPA lista para persistir.
     */
    public Paciente toEntity() {
        return Paciente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .correo(correo)
                .telefono(telefono)
                .fechaNacimiento(fechaNacimiento)
                .direccion(direccion)
                .build();
    }

    /**
     * Actualiza los campos editables de una entidad existente.
     */
    public void actualizarEntidad(Paciente paciente) {
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setCorreo(correo);
        paciente.setTelefono(telefono);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setDireccion(direccion);
    }
}
