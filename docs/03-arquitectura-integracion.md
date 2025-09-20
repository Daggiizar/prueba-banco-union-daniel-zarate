# Arquitectura y Tipo de Integración

Defino dos frentes: (1) el microservicio de **Clientes** y (2) la **integración** para matrícula/consulta/eliminación de cuentas con la Fintech.

## Decisiones principales
- Uso **Java 21** y **Spring Boot 3.4.5** con **Maven**.
- Expongo APIs REST síncronas.
- Organizo por capas: `controller`, `service`, `repository`, `dto`, `exception`, `config`.
- Valido entrada con **Jakarta Validation** (`@Valid`, constraints en DTOs).
- Centralizo errores con un **Global Exception Handler**.
- Dejo **logs** en archivo externo con nivel por entorno y trazabilidad por `idTx`.
- Manejo **perfiles** `dev`, `qa` y `prd` con propiedades **externas** usando `--spring.config.additional-location=file:./config/`.

## Persistencia
Para el microservicio de Clientes utilizo **H2** en modo archivo, con:
- Entidad `Client` (UUID id, document_type, document_number, …).
- Índice único (`document_type`, `document_number`) para evitar duplicados.
- Con esto cubro registro, actualización y consulta solicitados.

## Seguridad y calidad
- Autenticación/autorización por canal se define según el entorno (placeholder para integración real).
- Idempotencia: respeto `idTx` en flujos de integración; si recibo la misma operación, retorno el mismo resultado.
- Timeouts y reintentos controlados al llamar a la Fintech.
- Trazabilidad completa con `idTx`, tiempos y códigos HTTP.

## Contratos y nombres
- Endpoints del microservicio de Clientes: `/guardarCliente`, `/actualizarCliente`, `/consultarCliente/{tipo}_{numero}`.
- En integración de cuentas (desafío), trabajo con `/accounts` hacia la Fintech, adaptando si es necesario.

## Configuración externa (ejemplo)
- `config/` contendrá `application-dev.properties`, `application-qa.properties`, `application-prd.properties`.
- También parametrizo ahí la ruta de logs (por ejemplo `logging.file.name=./logs/app.log`).

