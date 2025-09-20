# Contrato de Servicio – Clientes (registro, actualización y consulta)

En esta sección defino los contratos que voy a exponer en el microservicio.
---

## 1) Campos y reglas comunes

| Atributo  | Descripción                                  | Obligatorio | Tipo   | Regla / Observación |
|-----------|----------------------------------------------|------------:|--------|---------------------|
| idTx      | Identificador único de la transacción         | Sí          | String | UUID v4 provisto por el canal |
| tipoDocumento | Tipo de documento del cliente            | Sí          | String | Valores permitidos (supuesto): `CC`, `CE`, `TI`, `PA` |
| numeroDocumento | Número de documento del cliente        | Sí          | String | Solo dígitos, 6–15 caracteres |
| primerNombre   | Primer nombre                            | Sí          | String | 1–60 caracteres |
| segundoNombre  | Segundo nombre                           | No          | String | 0–60 caracteres |
| primerApellido | Primer apellido                          | Sí          | String | 1–60 caracteres |
| segundoApellido| Segundo apellido                         | No          | String | 0–60 caracteres |
| telefono       | Teléfono de contacto                     | Sí          | Integer| 7–15 dígitos (supuesto) |
| correoElectronico | Correo del cliente                   | Sí          | String | Formato email válido (RFC simplificado) |

---

## 2) Endpoints

### 2.1 Registrar cliente
- **Método**: `POST`
- **Endpoint**: `/guardarCliente`
- **Request (JSON)**:
```json
{
  "idTx": "7f9c1c23-8b59-4a42-934a-9f7f2a6f9a10",
  "tipoDocumento": "CC",
  "numeroDocumento": "1234567890",
  "primerNombre": "Pepito",
  "segundoNombre": "Ramiro",
  "primerApellido": "Perez",
  "segundoApellido": "Gomez",
  "telefono": 3123456789,
  "correoElectronico": "pepito.perez@hotmail.com"
}
```

- **Validaciones**:
    - Campos obligatorios presentes y con formato correcto.
    - `correoElectronico` con patrón de email válido.
    - Cliente **no** existente previamente (`tipoDocumento` + `numeroDocumento`).
- **Responses**:
    - **200 OK** (registro exitoso)
      ```json
      {
        "idTx": "7f9c1c23-8b59-4a42-934a-9f7f2a6f9a10",
        "mensaje": "Cliente 1234567890 almacenado de forma exitosa."
      }
      ```
    - **400 Bad Request** (validación)
      ```json
      {
        "idTx": "7f9c1c23-8b59-4a42-934a-9f7f2a6f9a10",
        "error": "Campos tipoDocumento, numeroDocumento y correoElectronico son obligatorios. Campo correoElectronico no cumple formato."
      }
      ```
    - **409 Conflict** (cliente ya registrado)
      ```json
      {
        "idTx": "7f9c1c23-8b59-4a42-934a-9f7f2a6f9a10",
        "error": "Cliente CC 1234567890 ya se encuentra registrado."
      }
      ```
    - **500 Internal Server Error** (falla técnica)
      ```json
      {
        "idTx": "7f9c1c23-8b59-4a42-934a-9f7f2a6f9a10",
        "error": "Ocurrió un error no controlado al registrar el cliente."
      }
      ```

---

### 2.2 Actualizar cliente
- **Método**: `POST`
- **Endpoint**: `/actualizarCliente`
- **Request (JSON)**: misma estructura que registro.
- **Validaciones**:
    - Campos obligatorios válidos.
    - Cliente debe **existir** (`tipoDocumento` + `numeroDocumento`).
- **Responses**:
    - **200 OK**
      ```json
      {
        "idTx": "e3d9c5f1-42f8-4a31-a0d5-2b6d9fd1b0aa",
        "mensaje": "Cliente 1234567890 actualizado de forma exitosa."
      }
      ```
    - **400 Bad Request** (validación)
      ```json
      {
        "idTx": "e3d9c5f1-42f8-4a31-a0d5-2b6d9fd1b0aa",
        "error": "Campos obligatorios faltantes o formato inválido."
      }
      ```
    - **404 Not Found** (no existe)
      ```json
      {
        "idTx": "e3d9c5f1-42f8-4a31-a0d5-2b6d9fd1b0aa",
        "error": "Cliente CC 1234567890 no se encuentra registrado."
      }
      ```
    - **500 Internal Server Error** (falla técnica) – mismo formato de error genérico.

---

### 2.3 Consultar cliente
- **Método**: `GET`
- **Endpoint**: `/consultarCliente/{tipoDocumento}_{numeroDocumento}`
- **Response**:
    - **200 OK**
      ```json
      {
        "tipoDocumento": "CC",
        "numeroDocumento": "1234567890",
        "primerNombre": "Pepito",
        "segundoNombre": "Ramiro",
        "primerApellido": "Perez",
        "segundoApellido": "Gomez",
        "telefono": 3123456789,
        "correoElectronico": "pepito.perez@hotmail.com"
      }
      ```
    - **404 Not Found**
      ```json
      {
        "error": "Cliente CC 1234567890 no se encuentra registrado."
      }
      ```
    - **400 Bad Request** (si el path no cumple patrón)
      ```json
      { "error": "Formato de identificador inválido. Use {tipo}_{numero}." }
      ```

---

## 3) Mapa de códigos HTTP (resumen)

- `200 OK`: operación exitosa.
- `400 Bad Request`: validaciones de entrada.
- `404 Not Found`: cliente inexistente (consulta/actualización).
- `409 Conflict`: duplicado (registro).
- `500 Internal Server Error`: error técnico.

---

## 4) Trazabilidad y logs

En todas las respuestas incluyo `idTx` cuando corresponda. Registraré en el log: `timestamp`, `idTx`, `canal` (si aplica), `userId` (si aplica), operación, `httpCode`, `result`, `errorMessage`, `responseTimeMs`. La ruta del log la parametrizo vía configuración externa.

---

## 5) Persistencia

Para cumplir el requerimiento uso una base H2 (archivo) con el esquema mínimo para Clientes. En tiempo de implementación crearé la entidad `Client` y el repositorio correspondiente. La configuración de conexión la dejo en properties **externos** por perfil.