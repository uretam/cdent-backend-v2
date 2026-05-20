package com.cdent.pacientes.service;

import java.util.List;

import com.cdent.pacientes.dto.PacienteRequestDTO;
import com.cdent.pacientes.dto.PacienteResponseDTO;

/**
 * Contrato del servicio de negocio para operaciones CRUD de pacientes.
 */
public interface PacienteService {

    List<PacienteResponseDTO> findAll();

    PacienteResponseDTO findById(Long id);

    PacienteResponseDTO create(PacienteRequestDTO request);

    PacienteResponseDTO update(Long id, PacienteRequestDTO request);

    void delete(Long id);

    List<PacienteResponseDTO> findByApellido(String apellido);
}
