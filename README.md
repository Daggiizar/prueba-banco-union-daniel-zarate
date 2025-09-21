# Prueba Integraciones – Banco Unión (GAEI)

Este repositorio contiene los entregables solicitados: diseño UML, MER, contrato API, microservicio desarrollado en Spring Boot 3.4.5 (Java 21, Maven, H2) con validaciones, manejo de errores, logging externo y perfiles (`dev`, `qa`, `prd`).

---

## 📂 Entregables
- `docs/uml-sequence.png` – Diagrama de secuencia UML
- `docs/er-mer.png` – Modelo Entidad Relación
- `docs/contrato_api.md` – Contrato del servicio
- `docs/atributos_calidad.md` – Atributos de calidad definidos
- `docs/codigos_http.md` – Códigos HTTP de respuesta
- `docs/auditoria.md` – Definición de campos de auditoría
- `postman/` – Colección y environment de pruebas
- `src/main/resources/application-*.properties` – Configuración de perfiles y logging externo

---

## ▶️ Ejecución

### Requisitos
- Java 21
- Maven 3.9+

### Comandos
Dentro de `app/clients-service/`:

**Perfil dev**
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

**Perfil qa**
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=qa
```

**Perfil prd**
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prd
```

### Verificación
- **Base de datos:** H2 en modo file (`./data/clients_{perfil}`)
- **Logs:** generados en `./logs/clients-{perfil}.log`
- **Consola H2 (solo dev):** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 🌐 Endpoints

### 1. Registrar cliente
`POST /guardarCliente`

**Request**
```json
{
  "idTx": "TX-001",
  "tipoDocumento": "CC",
  "numeroDocumento": "1234567890",
  "primerNombre": "Juan",
  "segundoNombre": "Andrés",
  "primerApellido": "Pérez",
  "segundoApellido": "Gómez",
  "telefono": "3109876543",
  "correoElectronico": "juan.perez@example.com"
}
```

**Response éxito**
```json
{ "idTx": "TX-001", "mensaje": "Cliente 1234567890 almacenado de forma exitosa." }
```

---

### 2. Actualizar cliente
`POST /actualizarCliente`

**Request**
```json
{
  "idTx": "TX-002",
  "tipoDocumento": "CC",
  "numeroDocumento": "1234567890",
  "primerNombre": "Juan",
  "segundoNombre": "Andrés",
  "primerApellido": "Pérez",
  "segundoApellido": "Gómez",
  "telefono": "3209876543",
  "correoElectronico": "juan.perez@example.com"
}
```

**Response éxito**
```json
{ "idTx": "TX-002", "mensaje": "Cliente 1234567890 actualizado de forma exitosa." }
```

---

### 3. Consultar cliente
`GET /consultarCliente/{tipoDocumento}_{numeroDocumento}`

Ejemplo:  
`GET /consultarCliente/CC_1234567890`

**Response éxito**
```json
{
  "tipoDocumento": "CC",
  "numeroDocumento": "1234567890",
  "primerNombre": "Juan",
  "segundoNombre": "Andrés",
  "primerApellido": "Pérez",
  "segundoApellido": "Gómez",
  "telefono": "3209876543",
  "correoElectronico": "juan.perez@example.com"
}
```

---

## ⚠️ Escenarios de error (HTTP 400)

- **Campos faltantes**
```json
{ "idTx": "TX-001", "error": "Campos tipoDocumento , numeroDocumento. Son obligatorios." }
```

- **Email inválido**
```json
{ "idTx": "TX-001", "error": "Campo correoElectronico, no cumple con la estructura de un correo electrónico valido." }
```

- **Cliente duplicado**
```json
{ "idTx": "TX-001", "error": "Cliente CC 1234567890. Ya se encuentra registrado." }
```

- **Cliente no encontrado**
```json
{ "idTx": "TX-001", "error": "Cliente CC 1234567890. No se encuentra registrado." }
```

---

## 📝 Auditoría y Logs
- Cada método registra:
    - Request recibido
    - Respuesta de base de datos
    - Response enviado
    - Errores técnicos
- Los logs se almacenan en archivos externos por perfil:
    - `logs/clients-dev.log`
    - `logs/clients-qa.log`
    - `logs/clients-prd.log`

---

## 📦 Postman
En la carpeta `postman/` encontrarás:
- Colección de requests para los 3 endpoints
- Environment con `base_url=http://localhost:8080`

---

## ✅ Conclusión
El microservicio cumple con los requisitos de la prueba:
- Endpoints y contratos definidos
- Validaciones de request
- Manejo de errores centralizado (HTTP 400)
- Logging externo parametrizado por perfil
- BD en H2 file mode por perfil
