# Auditoría y Logs
Defino los siguientes campos mínimos en los registros de auditoría:

- **timestamp**: fecha y hora de la operación.
- **idTx**: identificador único de la transacción.
- **canal**: origen de la operación (ej. WEB, APP).
- **userId**: usuario que ejecuta la acción.
- **ip** y **userAgent**: datos técnicos de la conexión.
- **operación**: INSCRIBIR, CONSULTAR o ELIMINAR.
- **httpCode**: código de respuesta devuelto.
- **resultado**: SUCCESS o ERROR.
- **errorMessage**: en caso de error, detalle del problema.
- **responseTimeMs**: tiempo de ejecución en milisegundos.

Los logs los dejo almacenados en un archivo externo, cuya ruta se define en el archivo de configuración.