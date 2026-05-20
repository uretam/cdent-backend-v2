package com.cdent.tratamientos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdent.tratamientos.model.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    List<Tratamiento> findByActivoTrue();

    List<Tratamiento> findByCategoriaIgnoreCase(String categoria);
}
