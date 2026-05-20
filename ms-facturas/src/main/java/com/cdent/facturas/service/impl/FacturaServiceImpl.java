package com.cdent.facturas.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdent.facturas.client.CitaClient;
import com.cdent.facturas.dto.FacturaRequestDTO;
import com.cdent.facturas.dto.FacturaResponseDTO;
import com.cdent.facturas.exception.EntityNotFoundException;
import com.cdent.facturas.model.EstadoFactura;
import com.cdent.facturas.model.Factura;
import com.cdent.facturas.repository.FacturaRepository;
import com.cdent.facturas.service.FacturaService;

import feign.FeignException;

@Service
@Transactional
public class FacturaServiceImpl implements FacturaService {

    private static final Logger log = LoggerFactory.getLogger(FacturaServiceImpl.class);

    private final FacturaRepository facturaRepository;
    private final CitaClient citaClient;

    public FacturaServiceImpl(FacturaRepository facturaRepository, CitaClient citaClient) {
        this.facturaRepository = facturaRepository;
        this.citaClient = citaClient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaResponseDTO> findAll() {
        log.info("Inicio findAll facturas");
        List<FacturaResponseDTO> resultado = facturaRepository.findAll().stream()
                .map(FacturaResponseDTO::fromEntity).toList();
        log.info("Fin findAll facturas total={}", resultado.size());
        return resultado;
    }

    @Override
    @Transactional(readOnly = true)
    public FacturaResponseDTO findById(Long id) {
        log.info("Inicio findById factura id={}", id);
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con id: " + id));
        log.info("Fin findById factura id={}", id);
        return FacturaResponseDTO.fromEntity(factura);
    }

    @Override
    public FacturaResponseDTO create(FacturaRequestDTO request) {
        log.info("Inicio create factura citaId={}", request.getCitaId());
        validarCitaRemota(request.getCitaId());
        Factura guardada = facturaRepository.save(request.toEntity());
        log.info("Fin create factura id={}", guardada.getId());
        return FacturaResponseDTO.fromEntity(guardada);
    }

    @Override
    public FacturaResponseDTO update(Long id, FacturaRequestDTO request) {
        log.info("Inicio update factura id={}", id);
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada con id: " + id));
        validarCitaRemota(request.getCitaId());
        request.actualizarEntidad(factura);
        Factura actualizada = facturaRepository.save(factura);
        log.info("Fin update factura id={}", id);
        return FacturaResponseDTO.fromEntity(actualizada);
    }

    @Override
    public void delete(Long id) {
        log.info("Inicio delete factura id={}", id);
        if (!facturaRepository.existsById(id)) {
            throw new EntityNotFoundException("Factura no encontrada con id: " + id);
        }
        facturaRepository.deleteById(id);
        log.info("Fin delete factura id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalFacturadoPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        log.info("Inicio getTotalFacturadoPorPeriodo inicio={}, fin={}", fechaInicio, fechaFin);
        try {
            log.debug("Ping a ms-citas mediante CitaClient id=1");
            citaClient.obtenerCita(1L);
        } catch (FeignException ex) {
            log.warn("No se pudo contactar ms-citas en el ping de salud: {}", ex.getMessage());
        }
        BigDecimal total = facturaRepository.sumMontoTotalPagadoPorPeriodo(fechaInicio, fechaFin);
        if (total == null) {
            total = BigDecimal.ZERO;
        }
        log.info("Fin getTotalFacturadoPorPeriodo total={}", total);
        return total;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacturaResponseDTO> findFacturasPendientesPorPaciente(Long pacienteId) {
        log.info("Inicio findFacturasPendientesPorPaciente pacienteId={}", pacienteId);
        List<FacturaResponseDTO> resultado = facturaRepository
                .findByPacienteIdAndEstado(pacienteId, EstadoFactura.PENDING).stream()
                .map(FacturaResponseDTO::fromEntity)
                .toList();
        log.info("Fin findFacturasPendientesPorPaciente total={}", resultado.size());
        return resultado;
    }

    private void validarCitaRemota(Long citaId) {
        try {
            log.debug("Validando cita id={} vía CitaClient", citaId);
            citaClient.obtenerCita(citaId);
        } catch (FeignException ex) {
            throw new EntityNotFoundException("Cita no encontrada con id: " + citaId);
        }
    }
}
