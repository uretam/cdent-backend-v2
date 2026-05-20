package com.cdent.odontologos.dto;

import java.time.LocalDateTime;

import com.cdent.odontologos.model.Odontologo;

import lombok.Data;

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

    public static OdontologoResponseDTO fromEntity(Odontologo odontologo) {
        OdontologoResponseDTO dto = new OdontologoResponseDTO();
        dto.setId(odontologo.getId());
        dto.setNombre(odontologo.getNombre());
        dto.setApellido(odontologo.getApellido());
        dto.setCorreo(odontologo.getCorreo());
        dto.setEspecialidad(odontologo.getEspecialidad());
        dto.setNumeroLicencia(odontologo.getNumeroLicencia());
        dto.setTelefono(odontologo.getTelefono());
        dto.setActivo(odontologo.getActivo());
        dto.setFechaCreacion(odontologo.getFechaCreacion());
        return dto;
    }
}
