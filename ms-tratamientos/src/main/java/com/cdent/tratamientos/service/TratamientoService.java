package com.cdent.tratamientos.service;

import java.util.List;

import com.cdent.tratamientos.dto.TratamientoRequestDTO;
import com.cdent.tratamientos.dto.TratamientoResponseDTO;

public interface TratamientoService {

    List<TratamientoResponseDTO> findAll();

    TratamientoResponseDTO findById(Long id);

    TratamientoResponseDTO create(TratamientoRequestDTO request);

    TratamientoResponseDTO update(Long id, TratamientoRequestDTO request);

    void delete(Long id);

    List<TratamientoResponseDTO> findActivos();

    List<TratamientoResponseDTO> findByCategoria(String categoria);
}
