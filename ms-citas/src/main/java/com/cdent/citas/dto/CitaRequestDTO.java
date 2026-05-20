package com.cdent.citas.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cdent.citas.model.EstadoCita;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO de entrada para crear o actualizar una cita con sus detalles.
 */
@Data
public class CitaRequestDTO {

    @NotNull(message = "El pacienteId es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El odontologoId es obligatorio")
    private Long odontologoId;

    @NotNull(message = "La fecha de cita es obligatoria")
    private LocalDateTime fechaCita;

    @NotNull(message = "El estado es obligatorio")
    private EstadoCita estado;

    private String notas;

    @NotEmpty(message = "Debe incluir al menos un detalle")
    @Valid
    private List<DetalleCitaRequestDTO> detalles = new ArrayList<>();
}
