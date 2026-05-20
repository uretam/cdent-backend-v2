package com.cdent.citas.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.cdent.citas.model.Cita;
import com.cdent.citas.model.EstadoCita;

import lombok.Data;

/**
 * DTO de salida completo de una cita.
 */
@Data
public class CitaResponseDTO {

    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private LocalDateTime fechaCita;
    private EstadoCita estado;
    private String notas;
    private LocalDateTime fechaCreacion;
    private List<DetalleCitaResponseDTO> detalles;

    public static CitaResponseDTO fromEntity(Cita cita) {
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.setId(cita.getId());
        dto.setPacienteId(cita.getPacienteId());
        dto.setOdontologoId(cita.getOdontologoId());
        dto.setFechaCita(cita.getFechaCita());
        dto.setEstado(cita.getEstado());
        dto.setNotas(cita.getNotas());
        dto.setFechaCreacion(cita.getFechaCreacion());
        dto.setDetalles(cita.getDetalles().stream()
                .map(DetalleCitaResponseDTO::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }
}
