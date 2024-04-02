package com.financiero.cuentasmovimientosmicroservicio.service.impl;

import com.financiero.cuentasmovimientosmicroservicio.domain.Cuenta;
import com.financiero.cuentasmovimientosmicroservicio.repository.CuentaRepository;
import com.financiero.cuentasmovimientosmicroservicio.service.spec.ICuentaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(Map<String, Object> parametros) {
        String numeroCuenta = (String) parametros.getOrDefault("numeroCuenta", "");
        String tipoCuenta = (String) parametros.getOrDefault("tipoCuenta", "");
        double saldoInicial = (double) parametros.getOrDefault("saldoInicial", 0.0);
        double saldoTotal = (double) parametros.getOrDefault("saldoTotal", 0.0);
        String estado = (String) parametros.getOrDefault("estado", "");
        String identificacion = (String) parametros.getOrDefault("identificacion", "");

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setNumeroCuenta(numeroCuenta);
        nuevaCuenta.setTipoCuenta(tipoCuenta);
        nuevaCuenta.setSaldoInicial(saldoInicial);
        nuevaCuenta.setSaldoFinal(saldoTotal);
        nuevaCuenta.setEstado(estado);
        nuevaCuenta.setIdentificacionCliente(identificacion);

        createCuenta(nuevaCuenta);
    }

    @Override
    public Cuenta updateCuenta(Long id, Cuenta cuenta) {
        cuenta.setId(id);
        return cuentaRepository.save(cuenta);
    }

    @Override
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}