package com.daggiizar.clients.service;

import com.daggiizar.clients.dto.ClientRequestDto;
import com.daggiizar.clients.dto.ClientResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClientService {
    ClientResponseDto create(ClientRequestDto request);
    Page<ClientResponseDto> findAll(Pageable pageable);
    ClientResponseDto findById(UUID id);
    ClientResponseDto update(UUID id, ClientRequestDto request);
    ClientResponseDto findByDocument(String documentType, String documentNumber);
    ClientResponseDto updateByDocument(ClientRequestDto request);
    void delete(UUID id);
}
