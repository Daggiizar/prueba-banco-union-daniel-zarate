package com.daggiizar.clients.repository;

import com.daggiizar.clients.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByDocumentNumber(String documentNumber);
    Optional<Client> findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByEmail(String email);
}
