package com.cdent.tratamientos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cdent.tratamientos.model.Tratamiento;

import lombok.Data;

@Data
public class TratamientoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer duracionMinutes;
    private String categoria;
    private Boolean activo;
    private LocalDateTime fechaCreacion;

    public static TratamientoResponseDTO fromEntity(Tratamiento tratamiento) {
        TratamientoResponseDTO dto = new TratamientoResponseDTO();
        dto.setId(tratamiento.getId());
        dto.setNombre(tratamiento.getNombre());
        dto.setDescripcion(tratamiento.getDescripcion());
        dto.setPrecio(tratamiento.getPrecio());
        dto.setDuracionMinutes(tratamiento.getDuracionMinutes());
        dto.setCategoria(tratamiento.getCategoria());
        dto.setActivo(tratamiento.getActivo());
        dto.setFechaCreacion(tratamiento.getFechaCreacion());
        return dto;
    }
}
