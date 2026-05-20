package com.cdent.odontologos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdent.odontologos.model.Odontologo;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    List<Odontologo> findByActivoTrue();

    Optional<Odontologo> findByCorreo(String correo);
}
