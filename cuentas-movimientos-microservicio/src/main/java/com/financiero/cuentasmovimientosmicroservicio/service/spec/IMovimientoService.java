package com.financiero.cuentasmovimientosmicroservicio.service.spec;

import com.financiero.cuentasmovimientosmicroservicio.domain.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMovimientoService {
    List<Movimiento> getAllMovimientos();
    Optional<Movimiento> getMovimientoById(Long id);
    Movimiento createMovimiento(Movimiento movimiento);
    void deleteMovimiento(Long id);
    List<Movimiento> obtenerReporteMovimientos(LocalDate fechaInicio, LocalDate fechaFin, String clienteId);
}
