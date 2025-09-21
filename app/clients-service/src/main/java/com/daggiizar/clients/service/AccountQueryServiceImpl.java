package com.daggiizar.clients.service;

import com.daggiizar.clients.domain.Account;
import com.daggiizar.clients.dto.AccountResponseDto;
import com.daggiizar.clients.repository.AccountRepository;
import com.daggiizar.clients.service.spec.AccountSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository accountRepository;

    public AccountQueryServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<AccountResponseDto> search(String q, Pageable pageable) {
        Specification<Account> spec = AccountSpecs.active();
        if (StringUtils.hasText(q)) spec = spec.and(AccountSpecs.matches(q));

        // Orden requerido: 1) banco A-Z  2) alias/nombre A-Z
        Sort sort = Sort.by("bank.name").ascending()
                .and(Sort.by("alias").ascending())
                .and(Sort.by("holderFullName").ascending());

        Pageable sorted = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return accountRepository.findAll(spec, sorted).map(a -> {
            AccountResponseDto dto = new AccountResponseDto();
            dto.setId(a.getId());
            dto.setBank(a.getBank().getName());
            dto.setAccountType(a.getAccountType().getName());
            dto.setAccountNumber(a.getAccountNumber());
            dto.setPhoneNumber(a.getPhoneNumber());
            dto.setAccountName(
                    StringUtils.hasText(a.getAlias()) ? a.getAlias() : a.getHolderFullName()
            );
            dto.setHolderDocType(a.getHolderDocType());
            dto.setHolderDocNumber(a.getHolderDocNumber());
            return dto;
        });
    }
}
