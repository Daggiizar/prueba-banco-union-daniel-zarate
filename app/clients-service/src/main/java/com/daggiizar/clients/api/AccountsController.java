package com.daggiizar.clients.api;

import com.daggiizar.clients.dto.AccountResponseDto;
import com.daggiizar.clients.dto.ApiMessageResponse;
import com.daggiizar.clients.exception.NotFoundException;
import com.daggiizar.clients.service.AccountQueryService;
import com.daggiizar.clients.service.AccountService;
import com.daggiizar.clients.service.AuditService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/cuentas")
public class AccountsController {

    private final AccountQueryService service;
    private final AuditService auditService;
    private final AccountService accountService;

    public AccountsController(AccountQueryService service,
                              AuditService auditService,
                              AccountService accountService) {
        this.service = service;
        this.auditService = auditService;
        this.accountService = accountService;
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

        long t0 = System.currentTimeMillis();
        String tx = StringUtils.hasText(idTx) ? idTx : UUID.randomUUID().toString();
        String endpoint = "/cuentas";
        Map<String, Object> reqSummary = Map.of("q", q, "page", page, "size", size, "channel", channel);

        try {
            Page<AccountResponseDto> result = service.search(q, PageRequest.of(page, size));
            auditService.audit(tx, endpoint, "GET", reqSummary, result, 200, null, null, t0);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            auditService.audit(tx, endpoint, "GET", reqSummary,
                    Map.of("error", ex.getMessage()), 500, "UNEXPECTED_ERROR", ex.toString(), t0);
            throw ex;
        }
    }

    // ====== DELETE /cuentas/{id} ======
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiMessageResponse> delete(
            @PathVariable("id") UUID id,
            @RequestHeader(value = "X-IdTx", required = false) String idTx,
            @RequestHeader(value = "X-Channel", required = false) String channel
    ) {
        long t0 = System.currentTimeMillis();
        String tx = StringUtils.hasText(idTx) ? idTx : UUID.randomUUID().toString();
        String endpoint = "/cuentas/" + id;

        try {
            accountService.deleteById(id);
            var resp = new ApiMessageResponse(
                    tx,
                    "Cuenta " + id + " eliminada exitosamente. Programaciones futuras canceladas (simulado)."
            );
            auditService.audit(tx, endpoint, "DELETE",
                    Map.of("id", id.toString(), "channel", channel), resp, 200, null, null, t0);
            return ResponseEntity.ok(resp);
        } catch (NotFoundException nf) {
            auditService.audit(tx, endpoint, "DELETE",
                    Map.of("id", id.toString(), "channel", channel),
                    Map.of("error", nf.getMessage()), 404, "NOT_FOUND", nf.toString(), t0);
            throw nf;
        } catch (Exception ex) {
            auditService.audit(tx, endpoint, "DELETE",
                    Map.of("id", id.toString(), "channel", channel),
                    Map.of("error", ex.getMessage()), 500, "UNEXPECTED_ERROR", ex.toString(), t0);
            throw ex;
        }
    }
}
