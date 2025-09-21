package com.daggiizar.clients.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "account_type")
public class AccountType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 16, unique = true)
    private String code;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 12)
    private String estado = "ACTIVO";

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
