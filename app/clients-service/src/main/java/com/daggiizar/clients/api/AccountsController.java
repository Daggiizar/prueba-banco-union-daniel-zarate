package com.daggiizar.clients.api;

import com.daggiizar.clients.dto.AccountResponseDto;
import com.daggiizar.clients.service.AccountQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class AccountsController {

    private final AccountQueryService service;

    public AccountsController(AccountQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponseDto>> search(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestHeader(value = "X-IdTx", required = false) String idTx,
            @RequestHeader(value = "X-Channel", required = false) String channel
    ) {
        if (!StringUtils.hasText(q)) q = null;
        return ResponseEntity.ok(service.search(q, PageRequest.of(page, size)));
    }
}
