package com.daggiizar.clients.service;

import com.daggiizar.clients.dto.AccountResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountQueryService {
    Page<AccountResponseDto> search(String q, Pageable pageable);
}
