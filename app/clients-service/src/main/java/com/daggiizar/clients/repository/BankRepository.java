package com.daggiizar.clients.repository;

import com.daggiizar.clients.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {}
