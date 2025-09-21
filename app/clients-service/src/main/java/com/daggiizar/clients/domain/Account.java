package com.daggiizar.clients.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false) @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne(optional = false) @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @Column(length = 120)
    private String alias;

    // Para ahorros/corriente
    @Column(length = 32)
    private String accountNumber;

    // Para depósito electrónico
    @Column(length = 32)
    private String phoneNumber;

    // Titular
    @Column(nullable = false, length = 8)
    private String holderDocType;

    @Column(nullable = false, length = 40)
    private String holderDocNumber;

    @Column(nullable = false, length = 150)
    private String holderFullName;

    @Column(nullable = false, length = 12)
    private String estado = "ACTIVO";

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Bank getBank() { return bank; }
    public void setBank(Bank bank) { this.bank = bank; }
    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getHolderDocType() { return holderDocType; }
    public void setHolderDocType(String holderDocType) { this.holderDocType = holderDocType; }
    public String getHolderDocNumber() { return holderDocNumber; }
    public void setHolderDocNumber(String holderDocNumber) { this.holderDocNumber = holderDocNumber; }
    public String getHolderFullName() { return holderFullName; }
    public void setHolderFullName(String holderFullName) { this.holderFullName = holderFullName; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
