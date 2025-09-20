# Modelo Entidad – Relación

Defino las entidades necesarias para soportar la matrícula de cuentas digitales:

- **Cliente**: almacena la información personal básica (documento, nombres, apellidos, correo, teléfono).
- **CuentaInscrita**: contiene los datos de las cuentas asociadas al cliente, incluyendo número, tipo y estado.
- **HistorialOperacion**: registra cada acción de inscripción, consulta o eliminación, con fecha, hora y resultado.
- **Catálogos**: agrupa valores controlados como tipo de cuenta, estados y canales.

Este modelo me permite mantener la información consistente y garantizar la trazabilidad de todas las operaciones realizadas.
