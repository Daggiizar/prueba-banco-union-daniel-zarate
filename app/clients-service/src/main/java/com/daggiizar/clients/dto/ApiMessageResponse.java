package com.daggiizar.clients.dto;

// Respuesta genérica 200 con mensaje (guardar/actualizar).
import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiMessageResponse(
        @JsonProperty("idTx") String transactionId,
        @JsonProperty("mensaje") String message
) {}
