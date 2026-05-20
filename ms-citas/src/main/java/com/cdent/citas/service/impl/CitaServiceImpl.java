package com.cdent.citas.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdent.citas.client.OdontologoClient;
import com.cdent.citas.client.PacienteClient;
import com.cdent.citas.dto.CitaRequestDTO;
import com.cdent.citas.dto.CitaResponseDTO;
import com.cdent.citas.dto.DetalleCitaRequestDTO;
import com.cdent.citas.exception.EntityNotFoundException;
import com.cdent.citas.model.Cita;
import com.cdent.citas.model.DetalleCita;
import com.cdent.citas.repository.CitaRepository;
import com.cdent.citas.service.CitaService;

import feign.FeignException;

/**
 * Implementación del servicio de citas con validación remota vía Feign y cálculo automático de subtotales.
 */
@Service
@Transactional
public class CitaServiceImpl implements CitaService {

    private static final Logger log = LoggerFactory.getLogger(CitaServiceImpl.class);

    private final CitaRepository citaRepository;
    private final PacienteClient pacienteClient;
    private final OdontologoClient odontologoClient;

    public CitaServiceImpl(CitaRepository citaRepository, PacienteClient pacienteClient,
            OdontologoClient odontologoClient) {
        this.citaRepository = citaRepository;
        this.pacienteClient = pacienteClient;
        this.odontologoClient = odontologoClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaResponseDTO> findAll() {
        log.info("Inicio findAll citas");
        List<CitaResponseDTO> resultado = citaRepository.findAll().stream()
                .map(CitaResponseDTO::fromEntity)
                .toList();
        log.info("Fin findAll citas, total={}", resultado.size());
        return resultado;
    }

    @Override
    @Transactional(readOnly = true)
    public CitaResponseDTO findById(Long id) {
        log.info("Inicio findById cita id={}", id);
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con id: " + id));
        CitaResponseDTO dto = CitaResponseDTO.fromEntity(cita);
        log.info("Fin findById cita id={}", id);
        return dto;
    }

    @Override
    public CitaResponseDTO create(CitaRequestDTO request) {
        log.info("Inicio create cita pacienteId={}", request.getPacienteId());
        validarPacienteRemoto(request.getPacienteId());
        validarOdontologoRemoto(request.getOdontologoId());
        Cita cita = construirCitaDesdeRequest(request);
        Cita guardada = citaRepository.save(cita);
        CitaResponseDTO dto = CitaResponseDTO.fromEntity(guardada);
        log.info("Fin create cita id={}", dto.getId());
        return dto;
    }

    @Override
    public CitaResponseDTO update(Long id, CitaRequestDTO request) {
        log.info("Inicio update cita id={}", id);
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con id: " + id));
        validarPacienteRemoto(request.getPacienteId());
        validarOdontologoRemoto(request.getOdontologoId());
        cita.setPacienteId(request.getPacienteId());
        cita.setOdontologoId(request.getOdontologoId());
        cita.setFechaCita(request.getFechaCita());
        cita.setEstado(request.getEstado());
        cita.setNotas(request.getNotas());
        cita.getDetalles().clear();
        cita.getDetalles().addAll(construirDetalles(request.getDetalles(), cita));
        Cita actualizada = citaRepository.save(cita);
        CitaResponseDTO dto = CitaResponseDTO.fromEntity(actualizada);
        log.info("Fin update cita id={}", id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        log.info("Inicio delete cita id={}", id);
        if (!citaRepository.existsById(id)) {
            throw new EntityNotFoundException("Cita no encontrada con id: " + id);
        }
        citaRepository.deleteById(id);
        log.info("Fin delete cita id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaResponseDTO> findByPacienteId(Long pacienteId) {
        log.info("Inicio findByPacienteId pacienteId={}", pacienteId);
        try {
            log.debug("Validando paciente remoto id={} vía Feign", pacienteId);
            pacienteClient.obtenerPaciente(pacienteId);
        } catch (FeignException ex) {
            log.debug("Feign falló al consultar paciente id={}: {}", pacienteId, ex.getMessage());
            throw new EntityNotFoundException("Paciente no encontrado con id: " + pacienteId);
        }
        List<CitaResponseDTO> resultado = citaRepository.findByPacienteId(pacienteId).stream()
                .map(CitaResponseDTO::fromEntity)
                .toList();
        log.info("Fin findByPacienteId pacienteId={}, total={}", pacienteId, resultado.size());
        return resultado;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaResponseDTO> findByFechaCitaBetween(LocalDateTime inicio, LocalDateTime fin) {
        log.info("Inicio findByFechaCitaBetween inicio={}, fin={}", inicio, fin);
        List<CitaResponseDTO> resultado = citaRepository.findByFechaCitaBetween(inicio, fin).stream()
                .map(CitaResponseDTO::fromEntity)
                .toList();
        log.info("Fin findByFechaCitaBetween total={}", resultado.size());
        return resultado;
    }

    private void validarPacienteRemoto(Long pacienteId) {
        try {
            log.debug("Consultando paciente id={} con PacienteClient", pacienteId);
            pacienteClient.obtenerPaciente(pacienteId);
        } catch (FeignException ex) {
            throw new EntityNotFoundException("Paciente no encontrado con id: " + pacienteId);
        }
    }

    private void validarOdontologoRemoto(Long odontologoId) {
        try {
            log.debug("Consultando odontólogo id={} con OdontologoClient", odontologoId);
            odontologoClient.obtenerOdontologo(odontologoId);
        } catch (FeignException ex) {
            throw new EntityNotFoundException("Odontólogo no encontrado con id: " + odontologoId);
        }
    }

    private Cita construirCitaDesdeRequest(CitaRequestDTO request) {
        Cita cita = Cita.builder()
                .pacienteId(request.getPacienteId())
                .odontologoId(request.getOdontologoId())
                .fechaCita(request.getFechaCita())
                .estado(request.getEstado())
                .notas(request.getNotas())
                .detalles(new ArrayList<>())
                .build();
        cita.getDetalles().addAll(construirDetalles(request.getDetalles(), cita));
        return cita;
    }

    private List<DetalleCita> construirDetalles(List<DetalleCitaRequestDTO> detallesRequest, Cita cita) {
        List<DetalleCita> detalles = new ArrayList<>();
        for (DetalleCitaRequestDTO detalleReq : detallesRequest) {
            BigDecimal subtotal = detalleReq.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(detalleReq.getCantidad()));
            log.debug("Calculando subtotal cantidad={} precioUnitario={} subtotal={}",
                    detalleReq.getCantidad(), detalleReq.getPrecioUnitario(), subtotal);
            DetalleCita detalle = DetalleCita.builder()
                    .cita(cita)
                    .tratamientoId(detalleReq.getTratamientoId())
                    .cantidad(detalleReq.getCantidad())
                    .precioUnitario(detalleReq.getPrecioUnitario())
                    .subtotal(subtotal)
                    .notas(detalleReq.getNotas())
                    .build();
            detalles.add(detalle);
        }
        return detalles;
    }
}
