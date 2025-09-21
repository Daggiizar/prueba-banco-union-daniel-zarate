package com.daggiizar.clients.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {
    @Id @GeneratedValue
    private UUID id;

    @Column(nullable=false, length=10, unique=true)
    private String code;

    @Column(nullable=false, length=20)
    private String nit;

    @Column(nullable=false, length=120)
    private String name;

    @Column(nullable=false) private boolean allowsSavings;
    @Column(nullable=false) private boolean allowsChecking;
    @Column(nullable=false) private boolean allowsElectronic;

    @Column(nullable=false, length=12)
    private String estado = "ACTIVO";

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isAllowsSavings() { return allowsSavings; }
    public void setAllowsSavings(boolean v) { this.allowsSavings = v; }
    public boolean isAllowsChecking() { return allowsChecking; }
    public void setAllowsChecking(boolean v) { this.allowsChecking = v; }
    public boolean isAllowsElectronic() { return allowsElectronic; }
    public void setAllowsElectronic(boolean v) { this.allowsElectronic = v; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
