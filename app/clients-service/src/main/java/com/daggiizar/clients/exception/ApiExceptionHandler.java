package com.daggiizar.clients.exception;

import com.daggiizar.clients.dto.ApiErrorResponse;
import com.daggiizar.clients.dto.ClientRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler {

    // Mapa de nombre interno (DTO) -> nombre del contrato (es)
    private static final Map<String, String> FIELD_NAME_MAP = new LinkedHashMap<>() {{
        put("transactionId", "idTx");
        put("documentType", "tipoDocumento");
        put("documentNumber", "numeroDocumento");
        put("firstName", "primerNombre");
        put("middleName", "segundoNombre");
        put("lastName", "primerApellido");
        put("secondLastName", "segundoApellido");
        put("phoneNumber", "telefono");
        // OJO: la prueba usa "correElectronico" (sin 'o' tras 'corr'), mantenemos ese literal
        put("email", "correoElectronico");
    }};

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String idTx = "";
        Object target = ex.getBindingResult().getTarget();
        if (target instanceof ClientRequestDto dto && dto.transactionId() != null) {
            idTx = dto.transactionId();
        }

        // Separa errores por tipo: faltantes/blank vs email inválido
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        // Campos faltantes/obligatorios: NotBlank / NotNull
        String faltantes = fieldErrors.stream()
                .filter(fe -> "NotBlank".equalsIgnoreCase(fe.getCode()) || "NotNull".equalsIgnoreCase(fe.getCode()))
                .map(fe -> FIELD_NAME_MAP.getOrDefault(fe.getField(), fe.getField()))
                .distinct()
                .collect(Collectors.joining(" , "));

        // Email inválido
        boolean emailInvalido = fieldErrors.stream()
                .anyMatch(fe -> "email".equalsIgnoreCase(fe.getField()) && "Email".equalsIgnoreCase(fe.getCode()));

        StringBuilder msg = new StringBuilder();
        if (!faltantes.isEmpty()) {
            msg.append("Campos ").append(faltantes).append(". Son obligatorios.");
        }
        if (emailInvalido) {
            if (msg.length() > 0) msg.append(" ");
            msg.append("Campo correoElectronico, no cumple con la estructura de un correo electrónico valido.");
        }
        if (msg.length() == 0) {
            msg.append("Solicitud inválida.");
        }

        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiErrorResponse(idTx, msg.toString()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        // Duplicados → 400
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiErrorResponse("", ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(NotFoundException ex) {
        // 400 para "no encontrado"
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiErrorResponse("", ex.getMessage()));
    }
}
