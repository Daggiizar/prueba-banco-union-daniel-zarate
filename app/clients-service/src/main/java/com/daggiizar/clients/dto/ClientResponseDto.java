package com.daggiizar.clients.dto;

// DTO de salida para consultas exitosas.
import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientResponseDto(
        @JsonProperty("tipoDocumento") String documentType,
        @JsonProperty("numeroDocumento") String documentNumber,
        @JsonProperty("primerNombre") String firstName,
        @JsonProperty("segundoNombre") String middleName,
        @JsonProperty("primerApellido") String lastName,
        @JsonProperty("segundoApellido") String secondLastName,
        @JsonProperty("telefono") Integer phoneNumber,
        @JsonProperty("correoElectronico") String email
) {}