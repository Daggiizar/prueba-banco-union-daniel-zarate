# Arquitectura y Tipo de Integración

La integración la planteo como un microservicio REST desarrollado en Spring Boot.  
El microservicio expone operaciones de inscripción, consulta y eliminación de cuentas, y se comunica de forma síncrona con la Fintech.

Decisiones que tomé:
- Usar una arquitectura en capas (Controlador, Servicio y Repositorio).
- Validar los datos de entrada en el API antes de enviarlos a la Fintech.
- Manejar excepciones de forma centralizada.
- Registrar logs en cada paso de la operación.
- Configurar parámetros externos y perfiles separados para desarrollo, QA y producción.

De esta manera logro una solución ordenada, escalable y fácil de mantener.
