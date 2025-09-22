package com.daggiizar.clients.service;

import com.daggiizar.clients.exception.NotFoundException;
import com.daggiizar.clients.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        var acc = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cuenta no existe"));
        accountRepository.delete(acc);

        log.info("Simulación: cancelar programaciones futuras para la cuenta {}", id);
    }
}
