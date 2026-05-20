package com.cdent.facturas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cdent.facturas.dto.CitaResponseDTO;

@FeignClient(name = "ms-citas", url = "http://localhost:8082")
public interface CitaClient {

    @GetMapping("/api/citas/{id}")
    CitaResponseDTO obtenerCita(@PathVariable("id") Long id);
}
