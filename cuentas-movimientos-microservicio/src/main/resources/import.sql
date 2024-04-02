-- Datos de ejemplo para la tabla Cuenta
INSERT INTO cuentas (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_final, estado) VALUES ('1234567890', 'Corriente', 1000.00, 1000.00, 'Activa');
INSERT INTO cuentas (numero_cuenta, tipo_cuenta, saldo_inicial, saldo_final, estado) VALUES ('0987654321', 'Ahorro', 1000.00, 1000.00, 'Inactiva');

-- Datos de ejemplo para la tabla Movimiento
--INSERT INTO movimientos (id, fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES (1, '2024-03-31', 'Depósito', 200.00, 1200.00, 1);
--INSERT INTO movimientos (id, fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES (2, '2024-03-31', 'Retiro', -100.00, 1100.00, 1);
