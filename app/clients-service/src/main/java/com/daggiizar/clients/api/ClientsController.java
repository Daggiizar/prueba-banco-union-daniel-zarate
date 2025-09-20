package com.daggiizar.clients.api;

import com.daggiizar.clients.dto.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ClientsController {

    private static final Logger log = LoggerFactory.getLogger(ClientsController.class);

    // Endpoint para registrar cliente.
    @PostMapping("/guardarCliente")
    public ResponseEntity<ApiMessageResponse> saveClient(@Valid @RequestBody ClientRequestDto request) {
        log.info("Request /guardarCliente: {}", request);
        // TODO: llama a service para registrar
        var response = new ApiMessageResponse(
                request.transactionId(),
                "Cliente " + request.documentNumber() + " almacenado de forma exitosa. (TODO)"
        );
        log.info("Response /guardarCliente: {}", response);
        return ResponseEntity.ok(response);
    }

    // Endpoint para actualizar cliente.
    @PostMapping("/actualizarCliente")
    public ResponseEntity<ApiMessageResponse> updateClient(@Valid @RequestBody ClientRequestDto request) {
        log.info("Request /actualizarCliente: {}", request);
        // TODO: llamar a service para actualizar
        var response = new ApiMessageResponse(
                request.transactionId(),
                "Cliente " + request.documentNumber() + " actualizado de forma exitosa. (TODO)"
        );
        log.info("Response /actualizarCliente: {}", response);
        return ResponseEntity.ok(response);
    }

    // Endpoint para consultar cliente por tipoDocumento_numeroDocumento.
    @GetMapping("/consultarCliente/{tipoDocumento}_{numeroDocumento}")
    public ResponseEntity<ClientResponseDto> getClient(
            @PathVariable("tipoDocumento") String documentType,
            @PathVariable("numeroDocumento") String documentNumber) {
        log.info("Request /consultarCliente: {}_{}", documentType, documentNumber);
        // TODO: llamar a service para consulta
        var response = new ClientResponseDto(
                documentType, documentNumber, "TODO", null, "TODO", null, 12345678, "todo@example.com"
        );
        log.info("Response /consultarCliente: {}", response);
        return ResponseEntity.ok(response);
    }
}
