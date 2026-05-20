package com.cdent.citas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cdent.citas.dto.OdontologoResponseDTO;

/**
 * Cliente Feign hacia ms-odontologos para validar existencia de odontólogos.
 */
@FeignClient(name = "ms-odontologos", url = "http://localhost:8083")
public interface OdontologoClient {

    @GetMapping("/api/odontologos/{id}")
    OdontologoResponseDTO obtenerOdontologo(@PathVariable("id") Long id);
}
