package com.cdent.odontologos.service;

import java.util.List;

import com.cdent.odontologos.dto.OdontologoRequestDTO;
import com.cdent.odontologos.dto.OdontologoResponseDTO;

public interface OdontologoService {

    List<OdontologoResponseDTO> findAll();

    OdontologoResponseDTO findById(Long id);

    OdontologoResponseDTO create(OdontologoRequestDTO request);

    OdontologoResponseDTO update(Long id, OdontologoRequestDTO request);

    void delete(Long id);

    List<OdontologoResponseDTO> findActivos();
}
