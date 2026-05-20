package com.cdent.odontologos.dto;

import com.cdent.odontologos.model.Odontologo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OdontologoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El correo debe ser válido")
    private String correo;

    private String especialidad;

    @NotBlank(message = "El número de licencia es obligatorio")
    private String numeroLicencia;

    private String telefono;

    @NotNull(message = "El campo activo es obligatorio")
    private Boolean activo;

    public Odontologo toEntity() {
        return Odontologo.builder()
                .nombre(nombre)
                .apellido(apellido)
                .correo(correo)
                .especialidad(especialidad)
                .numeroLicencia(numeroLicencia)
                .telefono(telefono)
                .activo(activo)
                .build();
    }

    public void actualizarEntidad(Odontologo odontologo) {
        odontologo.setNombre(nombre);
        odontologo.setApellido(apellido);
        odontologo.setCorreo(correo);
        odontologo.setEspecialidad(especialidad);
        odontologo.setNumeroLicencia(numeroLicencia);
        odontologo.setTelefono(telefono);
        odontologo.setActivo(activo);
    }
}
