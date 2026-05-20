package com.cdent.tratamientos.dto;

import java.math.BigDecimal;

import com.cdent.tratamientos.model.Tratamiento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TratamientoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    @NotNull(message = "La duración en minutos es obligatoria")
    @Min(value = 1, message = "La duración debe ser al menos 1 minuto")
    private Integer duracionMinutes;

    private String categoria;

    @NotNull(message = "El campo activo es obligatorio")
    private Boolean activo;

    public Tratamiento toEntity() {
        return Tratamiento.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .precio(precio)
                .duracionMinutes(duracionMinutes)
                .categoria(categoria)
                .activo(activo)
                .build();
    }

    public void actualizarEntidad(Tratamiento tratamiento) {
        tratamiento.setNombre(nombre);
        tratamiento.setDescripcion(descripcion);
        tratamiento.setPrecio(precio);
        tratamiento.setDuracionMinutes(duracionMinutes);
        tratamiento.setCategoria(categoria);
        tratamiento.setActivo(activo);
    }
}
