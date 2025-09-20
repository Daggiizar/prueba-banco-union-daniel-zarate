# Diagrama de Secuencia – Matrícula, Consulta y Eliminación de Cuentas

A continuación describo, en orden, cómo fluyen las solicitudes entre los actores. Mantengo actores constantes:
- **Canal Digital**: app/web del banco.
- **API de Integración GAEI**: mi microservicio de integración.
- **Fintech**: servicio externo que procesa la cuenta.
- **Log/Auditoría**: salida a archivo externo con trazabilidad (`idTx`).

> Todas las operaciones registran trazas: entrada, salida, tiempos y errores, asociadas al `idTx`.

---

## 1) Inscripción de cuenta (Matrícula)

1. **Canal Digital → API Integración**: envío `POST /accounts/enroll` con `idTx`, `idUser`, `accountNumber`, `accountType`, `channel`.
2. **API Integración**: valido obligatoriedad y formato. Si falla, retorno **400** y finalizo.
3. **API Integración → Fintech**: transformo DTO y llamo al endpoint de la Fintech para inscribir la cuenta (timeout controlado).
4. **Fintech → API Integración**: responde **éxito** o **error** (ej.: ya inscrita).
5. **API Integración**:
    - Si éxito: compongo respuesta estándar y retorno **200** al canal.
    - Si cuenta ya existe: retorno **409** con mensaje claro.
    - Si error técnico/timeout: retorno **503** o **500** según el caso.
6. **API Integración → Log/Auditoría**: registro request, response, `idTx`, `userId`, operación, `httpCode`, `responseTimeMs`, y error si aplica.

---

## 2) Consulta de cuentas inscritas

1. **Canal Digital → API Integración**: envío `GET /accounts?userId=...` (o filtro equivalente) con `idTx`.
2. **API Integración**: valido parámetros y autorizaciones.
3. **API Integración → Fintech**: solicito el listado/estado de cuentas inscritas para el `userId`.
4. **Fintech → API Integración**: retorna la lista o vacío.
5. **API Integración**:
    - Si hay resultado: retorno **200** con el arreglo de cuentas.
    - Si no hay registros: retorno **200** con lista vacía (o **404** si el PO lo exige).
    - Si error/timeout: **503/500**.
6. **API Integración → Log/Auditoría**: guardo trazas con `idTx`.

---

## 3) Eliminación de cuenta inscrita

1. **Canal Digital → API Integración**: envío `DELETE /accounts/{accountNumber}` con `idTx` e identidad del usuario.
2. **API Integración**: valido existencia de parámetros y autorización del usuario sobre esa cuenta.
3. **API Integración → Fintech**: solicito eliminación de la inscripción.
4. **Fintech → API Integración**: confirma eliminación o indica que no existe.
5. **API Integración**:
    - Si éxito: retorno **200** con mensaje de confirmación.
    - Si la cuenta no está inscrita: **404** (o **409** si el PO define regla distinta).
    - Si error/timeout: **503/500**.
6. **API Integración → Log/Auditoría**: registro completo de la operación.

---

## Reglas transversales (aplican a las 3 operaciones)
- **Validaciones previas**: campos obligatorios, formatos y autorización de canal/usuario.
- **Timeouts y reintentos**: tiempo máximo hacia Fintech con reintentos acotados; si expira, devuelvo **503**.
- **Idempotencia**: si llega la misma inscripción con igual `idTx`, devuelvo el mismo resultado para evitar duplicados.
- **Trazabilidad**: todo se amarra a `idTx` y se escribe en log externo parametrizado por perfil (dev/qa/prd).
