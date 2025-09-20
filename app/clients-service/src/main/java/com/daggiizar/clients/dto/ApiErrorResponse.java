package com.daggiizar.clients.dto;

// Respuesta de error 400
import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiErrorResponse(
        @JsonProperty("idTx") String transactionId,
        @JsonProperty("error") String error
) {}
