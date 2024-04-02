package com.financiero.cuentasmovimientosmicroservicio.repository;

import com.financiero.cuentasmovimientosmicroservicio.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
