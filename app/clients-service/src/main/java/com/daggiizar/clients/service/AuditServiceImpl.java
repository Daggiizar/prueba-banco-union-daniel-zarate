package com.daggiizar.clients.service;

import com.daggiizar.clients.domain.AuditTransaction;
import com.daggiizar.clients.repository.AuditTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditTransactionRepository repo;
    private final ObjectMapper mapper;

    public AuditServiceImpl(AuditTransactionRepository repo, ObjectMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private String toJson(Object o) {
        if (o == null) return null;
        try { return mapper.writeValueAsString(o); }
        catch (Exception e) { return String.valueOf(o); }
    }

    @Override
    public void audit(String idTx, String endpoint, String httpMethod, Object requestObj, Object responseObj,
                      Integer httpStatus, String errorCode, String errorMessage, long startedAtMs) {
        AuditTransaction a = new AuditTransaction();
        a.setIdTx(idTx);
        a.setEndpoint(endpoint);
        a.setHttpMethod(httpMethod);
        a.setRequestBody(toJson(requestObj));
        a.setResponseBody(toJson(responseObj));
        a.setHttpStatus(httpStatus);
        a.setErrorCode(errorCode);
        a.setErrorMessage(errorMessage);
        a.setDurationMs(System.currentTimeMillis() - startedAtMs);
        repo.save(a);
    }
}
