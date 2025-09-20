# Modelo Entidad–Relación (Microservicio de Clientes)

En este modelo defino lo mínimo necesario para registrar, actualizar y consultar clientes.

## Entidad principal

### `client`
Representa a la persona que registro/actualizo/consulto.

| Columna            | Tipo (DB)     | Nulo | Regla / Observación |
|--------------------|---------------|:----:|---------------------|
| `id`               | UUID          |  No  | PK. Generado por el sistema. |
| `document_type`    | VARCHAR(5)    |  No  | Dominio: `CC`, `CE`, `TI`, `PA`. |
| `document_number`  | VARCHAR(15)   |  No  | Solo dígitos, longitud 6–15. |
| `first_name`       | VARCHAR(60)   |  No  | — |
| `middle_name`      | VARCHAR(60)   | Sí   | — |
| `last_name`        | VARCHAR(60)   |  No  | — |
| `second_last_name` | VARCHAR(60)   | Sí   | — |
| `phone`            | VARCHAR(15)   |  No  | Conservo como texto para no perder ceros/formatos internacionales. |
| `email`            | VARCHAR(254)  |  No  | Validación de email a nivel de API. |
| `created_at`       | TIMESTAMP     |  No  | Fecha/hora de creación. |
| `updated_at`       | TIMESTAMP     |  No  | Fecha/hora de última actualización. |

**Restricciones e índices**
- PK: `id`.
- Único: (`document_type`, `document_number`) para evitar duplicados.
- Chequeos lógicos (a nivel de API y/o DB) sobre longitud y patrón de `document_number`.
- Index sugerido en (`document_type`, `document_number`) para consultas rápidas.

**Relaciones**
- No defino relaciones adicionales en este alcance;La auditoría la resuelvo en logs de aplicación (archivo externo), no en tabla.

**Notas**
- Aunque en el contrato el teléfono se recibe como número, en persistencia lo guardo como texto para preservar ceros a la izquierda y formatos con prefijo país.
