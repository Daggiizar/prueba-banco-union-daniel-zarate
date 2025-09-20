# Diagrama de Secuencia – Matrícula, Consulta y Eliminación de Cuentas

En este diagrama represento el flujo de interacción entre los distintos actores:

1. El **Canal Digital** inicia la solicitud (inscripción, consulta o eliminación).
2. La petición llega al **API de Integración de GAEI**, donde valido la información y registro la trazabilidad.
3. Desde el API se envía la solicitud a la **Fintech**, que se encarga de procesar la operación sobre la cuenta.
4. Finalmente, la respuesta vuelve al **Canal Digital**, mostrando el resultado al usuario.

Con este flujo aseguro que cada operación queda registrada, validada y con trazabilidad de punta a punta.
