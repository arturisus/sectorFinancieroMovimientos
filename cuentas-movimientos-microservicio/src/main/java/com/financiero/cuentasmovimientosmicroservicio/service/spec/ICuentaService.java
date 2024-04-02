package com.financiero.cuentasmovimientosmicroservicio.service.spec;

import com.financiero.cuentasmovimientosmicroservicio.domain.Cuenta;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICuentaService {
    List<Cuenta> getAllCuentas();
    Optional<Cuenta> getCuentaById(Long id);
    Cuenta createCuenta(Cuenta cuenta);
    void receivedMessage(Map<String, Object> parametros);
    Cuenta updateCuenta(Long id, Cuenta cuenta);
    void deleteCuenta(Long id);
}
