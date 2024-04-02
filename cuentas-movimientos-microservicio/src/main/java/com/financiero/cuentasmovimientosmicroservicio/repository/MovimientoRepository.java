package com.financiero.cuentasmovimientosmicroservicio.repository;

import com.financiero.cuentasmovimientosmicroservicio.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}