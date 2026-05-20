package com.cdent.pacientes.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdent.pacientes.dto.PacienteRequestDTO;
import com.cdent.pacientes.dto.PacienteResponseDTO;
import com.cdent.pacientes.exception.EntityNotFoundException;
import com.cdent.pacientes.model.Paciente;
import com.cdent.pacientes.repository.PacienteRepository;
import com.cdent.pacientes.service.PacienteService;

/**
 * Implementación del servicio de pacientes con registro SLF4J en entrada y salida de cada método.
 */
@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    private static final Logger log = LoggerFactory.getLogger(PacienteServiceImpl.class);

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> findAll() {
        log.info("Inicio findAll pacientes");
        List<PacienteResponseDTO> resultado = pacienteRepository.findAll().stream()
                .map(PacienteResponseDTO::fromEntity)
                .toList();
        log.info("Fin findAll pacientes, total={}", resultado.size());
        return resultado;
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteResponseDTO findById(Long id) {
        log.info("Inicio findById paciente id={}", id);
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + id));
        PacienteResponseDTO dto = PacienteResponseDTO.fromEntity(paciente);
        log.info("Fin findById paciente id={}", id);
        return dto;
    }

    @Override
    public PacienteResponseDTO create(PacienteRequestDTO request) {
        log.info("Inicio create paciente correo={}", request.getCorreo());
        if (request.getCorreo() != null && pacienteRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un paciente con el correo: " + request.getCorreo());
        }
        Paciente guardado = pacienteRepository.save(request.toEntity());
        PacienteResponseDTO dto = PacienteResponseDTO.fromEntity(guardado);
        log.info("Fin create paciente id={}", dto.getId());
        return dto;
    }

    @Override
    public PacienteResponseDTO update(Long id, PacienteRequestDTO request) {
        log.info("Inicio update paciente id={}", id);
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + id));
        request.actualizarEntidad(paciente);
        Paciente actualizado = pacienteRepository.save(paciente);
        PacienteResponseDTO dto = PacienteResponseDTO.fromEntity(actualizado);
        log.info("Fin update paciente id={}", id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        log.info("Inicio delete paciente id={}", id);
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente no encontrado con id: " + id);
        }
        pacienteRepository.deleteById(id);
        log.info("Fin delete paciente id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> findByApellido(String apellido) {
        log.info("Inicio findByApellido apellido={}", apellido);
        List<PacienteResponseDTO> resultado = pacienteRepository.findByApellidoContainingIgnoreCase(apellido).stream()
                .map(PacienteResponseDTO::fromEntity)
                .toList();
        log.info("Fin findByApellido apellido={}, total={}", apellido, resultado.size());
        return resultado;
    }
}
