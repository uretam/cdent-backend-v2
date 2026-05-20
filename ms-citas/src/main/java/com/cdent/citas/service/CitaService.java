package com.cdent.citas.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cdent.citas.dto.CitaRequestDTO;
import com.cdent.citas.dto.CitaResponseDTO;

public interface CitaService {

    List<CitaResponseDTO> findAll();

    CitaResponseDTO findById(Long id);

    CitaResponseDTO create(CitaRequestDTO request);

    CitaResponseDTO update(Long id, CitaRequestDTO request);

    void delete(Long id);

    List<CitaResponseDTO> findByPacienteId(Long pacienteId);

    List<CitaResponseDTO> findByFechaCitaBetween(LocalDateTime inicio, LocalDateTime fin);
}
