package com.daggiizar.clients.service;

public interface AuditService {
    void audit(String idTx,
               String endpoint,
               String httpMethod,
               Object requestObj,
               Object responseObj,
               Integer httpStatus,
               String errorCode,
               String errorMessage,
               long startedAtMs);
}
