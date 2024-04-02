package com.financiero.cuentasmovimientosmicroservicio.service.impl;

import com.financiero.cuentasmovimientosmicroservicio.domain.Cuenta;
import com.financiero.cuentasmovimientosmicroservicio.domain.Movimiento;
import com.financiero.cuentasmovimientosmicroservicio.repository.CuentaRepository;
import com.financiero.cuentasmovimientosmicroservicio.repository.MovimientoRepository;
import com.financiero.cuentasmovimientosmicroservicio.service.SaldoInsuficienteException;
import com.financiero.cuentasmovimientosmicroservicio.service.spec.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    @Override
    public Movimiento createMovimiento(Movimiento movimiento) {
        Cuenta cuenta = movimiento.getCuenta();
        Cuenta cuentaExistente = cuentaRepository.findById(cuenta.getId())
                .orElseThrow(() -> new RuntimeException("La cuenta no existe"));

        double saldoFinal = cuentaExistente.getSaldoFinal();
        double valorMovimiento = Math.abs(movimiento.getValor());
        double saldoActual = movimiento.getTipoMovimiento().equals("Retiro") ?
                saldoFinal - valorMovimiento :
                saldoFinal + valorMovimiento;

        if (saldoActual < 0) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        cuentaExistente.setSaldoFinal(saldoActual);
        Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente);

        movimiento.setCuenta(cuentaActualizada);
        movimiento.setSaldo(saldoActual);

        return movimientoRepository.save(movimiento);
    }

    @Override
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> obtenerReporteMovimientos(LocalDate fechaInicio,
                                                      LocalDate fechaFin,
                                                      String numeroCuenta) {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientos.stream()
                .filter(movimiento ->
                        movimiento.getCuenta().getNumeroCuenta().equals(numeroCuenta) &&
                                (movimiento.getFecha().isEqual(fechaInicio)
                                        || movimiento.getFecha().isAfter(fechaInicio)) &&
                                (movimiento.getFecha().isEqual(fechaFin)
                                        || movimiento.getFecha().isBefore(fechaFin)))
                .collect(Collectors.toList());
    }
}