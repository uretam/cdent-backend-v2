package com.cdent.citas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cdent.citas.dto.PacienteResponseDTO;

/**
 * Cliente Feign hacia ms-pacientes para validar existencia de pacientes.
 */
@FeignClient(name = "ms-pacientes", url = "http://localhost:8081")
public interface PacienteClient {

    @GetMapping("/api/pacientes/{id}")
    PacienteResponseDTO obtenerPaciente(@PathVariable("id") Long id);
}
