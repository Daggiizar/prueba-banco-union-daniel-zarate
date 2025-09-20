package com.daggiizar.clients.exception;

// Maneja errores de validación y arma mensaje consolidado con nombres de campos del contrato.
import com.daggiizar.clients.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    // Mapa de nombre interno (inglés) -> nombre contrato (español).
    private static final Map<String, String> FIELD_NAME_MAP = new LinkedHashMap<>() {{
        put("transactionId", "idTx");
        put("documentType", "tipoDocumento");
        put("documentNumber", "numeroDocumento");
        put("firstName", "primerNombre");
        put("middleName", "segundoNombre");
        put("lastName", "primerApellido");
        put("secondLastName", "segundoApellido");
        put("phoneNumber", "telefono");
        put("email", "correElectronico");
    }};

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        var fields = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> FIELD_NAME_MAP.getOrDefault(err.getField(), err.getField()))
                .distinct()
                .collect(Collectors.joining(" , "));

        var message = "Campos " + fields + ". Son obligatorios o inválidos.";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse("", message));
    }
}
