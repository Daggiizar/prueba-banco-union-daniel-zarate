package com.daggiizar.clients.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

// DTO de entrada para registrar/actualizar cliente con validaciones.
public record ClientRequestDto(
        // Identificador de la transacción.
        @NotBlank @JsonProperty("idTx")
        String transactionId,
        // Tipo de documento (CC, CE, etc.).
        @NotBlank @JsonProperty("tipoDocumento")
        String documentType,
        @NotBlank @JsonProperty("numeroDocumento")
        String documentNumber,
        @NotBlank @JsonProperty("primerNombre")
        String firstName,
        @JsonProperty("segundoNombre")
        String middleName,
        @NotBlank @JsonProperty("primerApellido")
        String lastName,
        @JsonProperty("segundoApellido")
        String secondLastName,
        // Acepta "telefono" o "teléfono".
        @NotNull
        @JsonProperty("telefono")
        @JsonAlias({"teléfono"})
        String phoneNumber,
        @NotBlank @Email
        @JsonProperty("correoElectronico")
        String email
) {}