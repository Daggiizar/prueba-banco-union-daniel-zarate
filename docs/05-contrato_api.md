# Contrato de Servicio – Inscripción, Consulta y Eliminación de Cuentas

## Campos comunes en las solicitudes
| Atributo             | Descripción                                | Obligatorio | Tipo    | Restricción           |
|-----------------------|--------------------------------------------|-------------|---------|-----------------------|
| idTx                  | Identificador único de la transacción      | Sí          | String  | UUID v4               |
| idUser                | Usuario del canal que hace la operación    | Sí          | String  | Alfanumérico          |

## Ejemplo – Inscripción de cuenta (Request)
```json
{
  "idTx": "123e4567-e89b-12d3-a456-426614174000",
  "idUser": "CC1142547894",
  "numeroCuenta": "123456789",
  "tipoCuenta": "AHORROS",
  "canal": "WEB"
}

## Ejemplo – Inscripción de cuenta (Response Success
```json
{
   "idTx": "123e4567-e89b-12d3-a456-426614174000",
   "mensaje": "La cuenta 123456789 fue inscrita de manera exitosa."
}

## Ejemplo - Inscripción de cuenta (Response Error)
```json
{
  "idTx": "123e4567-e89b-12d3-a456-426614174000",
  "error": "La cuenta ya se encuentra inscrita."
}
