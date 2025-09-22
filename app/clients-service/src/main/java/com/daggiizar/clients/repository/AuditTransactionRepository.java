package com.daggiizar.clients.repository;

import com.daggiizar.clients.domain.AuditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuditTransactionRepository extends JpaRepository<AuditTransaction, UUID> {}
