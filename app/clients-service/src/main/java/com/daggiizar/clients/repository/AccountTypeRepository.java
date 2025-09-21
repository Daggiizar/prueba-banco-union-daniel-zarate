package com.daggiizar.clients.repository;

import com.daggiizar.clients.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AccountTypeRepository extends JpaRepository<AccountType, UUID> {}
