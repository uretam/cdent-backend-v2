package com.cdent.citas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdent.citas.model.Cita;

/**
 * Repositorio de citas con consultas derivadas en inglés.
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByPacienteId(Long pacienteId);

    List<Cita> findByFechaCitaBetween(LocalDateTime inicio, LocalDateTime fin);
}
