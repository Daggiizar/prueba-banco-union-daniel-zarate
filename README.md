# Prueba Integraciones – Banco Unión (GAEI)

Este repo contiene: diseño (UML, MER), contrato API y microservicio (Spring Boot 3.4.5, Java 21, Maven, H2).

## Entregables
- `docs/uml-sequence.png`
- `docs/er-mer.png`
- `docs/contrato_api.md`
- `docs/atributos_calidad.md`
- `docs/codigos_http.md`
- `docs/auditoria.md`
- `postman/` (colección y environment)
- `config/` (`application-*.properties` y configuración de logs **externos**)

## Cómo ejecutar (borrador)
Se usará `--spring.config.additional-location=file:./config/` para leer properties externos y perfiles `dev/qa/prd`.