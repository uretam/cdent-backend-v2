package com.cdent.tratamientos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdent.tratamientos.dto.TratamientoRequestDTO;
import com.cdent.tratamientos.dto.TratamientoResponseDTO;
import com.cdent.tratamientos.exception.EntityNotFoundException;
import com.cdent.tratamientos.model.Tratamiento;
import com.cdent.tratamientos.repository.TratamientoRepository;
import com.cdent.tratamientos.service.TratamientoService;

@Service
@Transactional
public class TratamientoServiceImpl implements TratamientoService {

    private static final Logger log = LoggerFactory.getLogger(TratamientoServiceImpl.class);

    private final TratamientoRepository tratamientoRepository;

    public TratamientoServiceImpl(TratamientoRepository tratamientoRepository) {
        this.tratamientoRepository = tratamientoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TratamientoResponseDTO> findAll() {
        log.info("Inicio findAll tratamientos");
        List<TratamientoResponseDTO> resultado = tratamientoRepository.findAll().stream()
                .map(TratamientoResponseDTO::fromEntity).toList();
        log.info("Fin findAll tratamientos total={}", resultado.size());
        return resultado;
    }

    @Override
    @Transactional(readOnly = true)
    public TratamientoResponseDTO findById(Long id) {
        log.info("Inicio findById tratamiento id={}", id);
        Tratamiento tratamiento = tratamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tratamiento no encontrado con id: " + id));
        log.info("Fin findById tratamiento id={}", id);
        return TratamientoResponseDTO.fromEntity(tratamiento);
    }

    @Override
    public TratamientoResponseDTO create(TratamientoRequestDTO request) {
        log.info("Inicio create tratamiento nombre={}", request.getNombre());
        Tratamiento guardado = tratamientoRepository.save(request.toEntity());
        log.info("Fin create tratamiento id={}", guardado.getId());
        return TratamientoResponseDTO.fromEntity(guardado);
    }

    @Override
    public TratamientoResponseDTO update(Long id, TratamientoRequestDTO request) {
        log.info("Inicio update tratamiento id={}", id);
        Tratamiento tratamiento = tratamientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tratamiento no encontrado con id: " + id));
        request.actualizarEntidad(tratamiento);
        Tratamiento actualizado = tratamientoRepository.save(tratamiento);
        log.info("Fin update tratamiento id={}", id);
        return TratamientoResponseDTO.fromEntity(actualizado);
    }

    @Override
    public void delete(Long id) {
        log.info("Inicio delete tratamiento id={}", id);
        if (!tratamientoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tratamiento no encontrado con id: " + id);
        }
        tratamientoRepository.deleteById(id);
        log.info("Fin delete tratamiento id={}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TratamientoResponseDTO> findActivos() {
        log.info("Inicio findActivos tratamientos");
        return tratamientoRepository.findByActivoTrue().stream()
                .map(TratamientoResponseDTO::fromEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TratamientoResponseDTO> findByCategoria(String categoria) {
        log.info("Inicio findByCategoria categoria={}", categoria);
        return tratamientoRepository.findByCategoriaIgnoreCase(categoria).stream()
                .map(TratamientoResponseDTO::fromEntity).toList();
    }
}
