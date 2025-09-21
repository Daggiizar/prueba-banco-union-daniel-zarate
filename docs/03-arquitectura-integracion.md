# Arquitectura y Tipo de Integración

## Decisiones principales
- **Lenguaje/Framework**: Java 21 + Spring Boot 3.4.5, empaquetado con Maven.
- **Estilo arquitectónico**: Microservicio independiente, sin estado, expuesto como API REST síncrona.
- **Capas internas**:
  - `api` → controladores REST
  - `service` → lógica de negocio
  - `repository` → acceso a datos con Spring Data JPA
  - `dto` → contratos de entrada/salida
  - `exception` → manejo centralizado de errores
- **Validación de entrada**: `@Valid` + anotaciones de Jakarta Validation en los DTOs.
- **Errores**: centralizados en `ApiExceptionHandler`, devolviendo mensajes claros en español.
- **Logs**: gestionados con SLF4J/Logback, escritos en archivos externos por perfil, siempre trazando `idTx`.
- **Perfiles**: `dev`, `qa` y `prd`, configurados mediante `application-*.properties` externos (`--spring.config.additional-location=file:./config/`).

---

## Persistencia
- **Base de datos**: H2 en modo archivo, una por perfil.
- **Entidad**: `Client` con `UUID id` como clave primaria.
- **Restricciones**: únicas en `document_number` y `email`.
- **Configuración automática**: `spring.jpa.hibernate.ddl-auto=update`.

Esto soporta los escenarios de **registro**, **actualización** y **consulta** de clientes.

---

## Seguridad y calidad
- **Validaciones funcionales**: unicidad de documento/email, formato de correo electrónico.
- **Idempotencia**: uso de `idTx` como identificador de transacción.
- **Trazabilidad**: cada request registra entrada, operación en base de datos y respuesta con códigos HTTP.
- **Robustez**: manejo controlado de duplicados, “no encontrado” y validaciones obligatorias.
- **Escalabilidad**: servicio stateless, desplegable en contenedores, escalable horizontalmente.

---

## Contratos y endpoints
- **`/guardarCliente`** → `POST`, registro de cliente.
- **`/actualizarCliente`** → `POST`, actualización de cliente por tipo/número documento.
- **`/consultarCliente/{tipoDocumento}_{numeroDocumento}`** → `GET`, consulta de cliente.
- **`/consultarClientes`** → `GET`, listado paginado (apoyo a pruebas).

---

## Configuración externa
- **Carpeta `config/`** con:
  - `application-dev.properties`
  - `application-qa.properties`
  - `application-prd.properties`
- **Archivos de logs** en carpeta `logs/` por perfil:
  - `clients-dev.log`
  - `clients-qa.log`
  - `clients-prd.log`  
