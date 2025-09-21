package com.daggiizar.clients.service.impl;

import com.daggiizar.clients.domain.Client;
import com.daggiizar.clients.dto.ClientRequestDto;
import com.daggiizar.clients.dto.ClientResponseDto;
import com.daggiizar.clients.exception.NotFoundException;
import com.daggiizar.clients.repository.ClientRepository;
import com.daggiizar.clients.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository repo;

    public ClientServiceImpl(ClientRepository repo) {
        this.repo = repo;
    }

    @Override
    public ClientResponseDto create(ClientRequestDto req) {
        if (req.documentNumber() != null && repo.existsByDocumentNumber(req.documentNumber())) {
            throw new IllegalArgumentException("Cliente " + req.documentType() + " " + req.documentNumber() + ". Ya se encuentra registrado.");
        }
        if (req.email() != null && repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Cliente " + req.documentType() + " " + req.documentNumber() + ". Ya se encuentra registrado.");
        }
        Client c = toEntity(new Client(), req);
        c = repo.save(c);
        log.info("DB response save:{}", c);
        return toResponse(c);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientResponseDto> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDto findById(UUID id) {
        Client c = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no se encuentra registrado."));
        return toResponse(c);
    }

    @Override
    public ClientResponseDto update(UUID id, ClientRequestDto req) {
        Client c = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no se encuentra registrado."));
        c = toEntity(c, req);
        c = repo.save(c);
        log.info("DB response updateById: {}", c);
        return toResponse(c);
    }

    @Override
    public void delete(UUID id) {
        Client c = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no se encuentra registrado."));
        c.setStatus("INACTIVE");
        repo.save(c);
        log.info("DB response delete (soft): {}", c);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDto findByDocument(String documentType, String documentNumber) {
        Client c = repo.findByDocumentTypeAndDocumentNumber(documentType, documentNumber)
                .orElseThrow(() -> new NotFoundException("Cliente " + documentType + " " + documentNumber + " no se encuentra registrado."));
        log.info("DB response findByDocument: {}", c);
        return toResponse(c);
    }

    @Override
    public ClientResponseDto updateByDocument(ClientRequestDto req) {
        Client c = repo.findByDocumentTypeAndDocumentNumber(
                        req.documentType(), req.documentNumber())
                .orElseThrow(() -> new NotFoundException("Cliente " + req.documentType() + " " + req.documentNumber() + " no se encuentra registrado."));
        c = toEntity(c, req);
        c = repo.save(c);
        log.info("DB response update: {}", c);
        return toResponse(c);
    }

    // ---- mapeos simples ----
    private Client toEntity(Client c, ClientRequestDto r) {
        c.setDocumentType(r.documentType());
        c.setDocumentNumber(r.documentNumber());
        c.setFirstName(r.firstName());
        c.setMiddleName(r.middleName());
        c.setLastName(r.lastName());
        c.setSecondLastName(r.secondLastName());
        c.setEmail(r.email());
        c.setPhone(r.phoneNumber());
        return c;
    }

    private ClientResponseDto toResponse(Client c) {
        return new ClientResponseDto(
                c.getDocumentType(),
                c.getDocumentNumber(),
                c.getFirstName(),
                c.getMiddleName(),
                c.getLastName(),
                c.getSecondLastName(),
                c.getPhone(),
                c.getEmail()
        );
    }
}
