package com.cdent.facturas.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdent.facturas.model.EstadoFactura;
import com.cdent.facturas.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findByPacienteIdAndEstado(Long pacienteId, EstadoFactura estado);

    @Query("SELECT SUM(f.montoTotal) FROM Factura f WHERE f.estado = com.cdent.facturas.model.EstadoFactura.PAID "
            + "AND f.fechaEmision BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal sumMontoTotalPagadoPorPeriodo(@Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);
}
