package com.daggiizar.clients.repository;

import com.daggiizar.clients.domain.BankAccountRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BankAccountRuleRepository extends JpaRepository<BankAccountRule, UUID> {}
