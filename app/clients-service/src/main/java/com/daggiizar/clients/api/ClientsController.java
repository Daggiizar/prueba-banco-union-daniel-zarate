package com.daggiizar.clients.api;

import com.daggiizar.clients.dto.*;
import com.daggiizar.clients.service.ClientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ClientsController {

    private static final Logger log = LoggerFactory.getLogger(ClientsController.class);
    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Endpoint para registrar cliente.
    @PostMapping("/guardarCliente")
    public ResponseEntity<ApiMessageResponse> saveClient(@Valid @RequestBody ClientRequestDto request) {
        log.info("Request /guardarCliente: {}", request);
        clientService.create(request); // llamada real al servicio
        var response = new ApiMessageResponse(
                request.transactionId(),
                "Cliente " + request.documentNumber() + " almacenado de forma exitosa."
        );
        log.info("Response /guardarCliente: {}", response);
        return ResponseEntity.ok(response); // 200 OK
    }

    // Endpoint para actualizar cliente.
    @PostMapping("/actualizarCliente")
    public ResponseEntity<ApiMessageResponse> updateClient(@Valid @RequestBody ClientRequestDto request) {
        log.info("Request /actualizarCliente: {}", request);
        clientService.updateByDocument(request); // actualiza por tipo+numero
        var response = new ApiMessageResponse(
                request.transactionId(),
                "Cliente " + request.documentNumber() + " actualizado de forma exitosa."
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
        var response = clientService.findByDocument(documentType, documentNumber); // consulta real
        log.info("Response /consultarCliente: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/consultarClientes")
    public ResponseEntity<?> getClients(org.springframework.data.domain.Pageable pageable) {
        return ResponseEntity.ok(clientService.findAll(pageable));
    }

}
