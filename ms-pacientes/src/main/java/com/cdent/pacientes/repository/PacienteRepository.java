package com.cdent.pacientes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdent.pacientes.model.Paciente;

/**
 * Repositorio Spring Data JPA para la entidad Paciente.
 * Los métodos derivados usan prefijos en inglés y atributos en español.
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * Busca un paciente por su correo electrónico único.
     */
    Optional<Paciente> findByCorreo(String correo);

    /**
     * Lista pacientes cuyo apellido contiene el texto indicado (sin distinguir mayúsculas).
     */
    List<Paciente> findByApellidoContainingIgnoreCase(String apellido);
}
