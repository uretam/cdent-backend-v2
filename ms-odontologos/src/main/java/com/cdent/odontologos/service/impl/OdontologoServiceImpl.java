package com.cdent.odontologos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdent.odontologos.dto.OdontologoRequestDTO;
import com.cdent.odontologos.dto.OdontologoResponseDTO;
import com.cdent.odontologos.exception.EntityNotFoundException;
import com.cdent.odontologos.model.Odontologo;
import com.cdent.odontologos.repository.OdontologoRepository;
import com.cdent.odontologos.service.OdontologoService;

@Service
@Transactional
public class OdontologoServiceImpl implements OdontologoService {

    private static final Logger log = LoggerFactory.getLogger(OdontologoServiceImpl.class);

    private final OdontologoRepository odontologoRepository;

    public OdontologoServiceImpl(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OdontologoResponseDTO> findAll() {
        log.info("Inicio findAll odontologos");
        List<OdontologoResponseDTO> resultado = odontologoRepository.findAll().stream()
                .map(OdontologoResponseDTO::fromEntity).toList();
        log.info("Fin findAll odontologos total={}", resultado.size());
        return resultado;
    }

    @Override
    @Transactional(readOnly = true)
    public OdontologoResponseDTO findById(Long id) {
        log.info("Inicio findById odontologo id={}", id);
        Odontologo odontologo = odontologoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Odontólogo no encontrado con id: " + id));
        log.info("Fin findById odontologo id={}", id);
        return OdontologoResponseDTO.fromEntity(odontologo);
    }

    @Override
    public OdontologoResponseDTO create(OdontologoRequestDTO request) {
        log.info("Inicio create odontologo licencia={}", request.getNumeroLicencia());
        Odontologo guardado = odontologoRepository.save(request.toEntity());
        log.info("Fin create odontologo id={}", guardado.getId());
        return OdontologoResponseDTO.fromEntity(guardado);
    }

    @Override
    public OdontologoResponseDTO update(Long id, OdontologoRequestDTO request) {
        log.info("Inicio update odontologo id={}", id);
        Odontologo odontologo = odontologoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Odontólogo no encontrado con id: " + id));
        request.actualizarEntidad(odontologo);
        Odontologo actualizado = odontologoRepository.save(odontologo);
        log.info("Fin update odontologo id={}", id);
        return OdontologoResponseDTO.fromEntity(actualizado);
    }

    @Override
    public void delete(Long id) {
        log.info("Inicio delete odontologo id={}", id);
        if (!odontologoRepository.existsById(id)) {
            throw new EntityNotFoundException("Odontólogo no encontrado con id: " + id);
        }
        odontologoRepository.deleteById(id);
        log.info("Fin delete odontologo id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OdontologoResponseDTO> findActivos() {
        log.info("Inicio findActivos odontologos");
        List<OdontologoResponseDTO> resultado = odontologoRepository.findByActivoTrue().stream()
                .map(OdontologoResponseDTO::fromEntity).toList();
        log.info("Fin findActivos total={}", resultado.size());
        return resultado;
    }
}
