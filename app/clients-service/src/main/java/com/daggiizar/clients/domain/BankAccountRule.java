package com.daggiizar.clients.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(
        name = "bank_account_rule",
        uniqueConstraints = @UniqueConstraint(columnNames = {"bank_id", "account_type_id"})
)
public class BankAccountRule {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @Column(nullable = false)
    private int minLength;

    @Column(nullable = false)
    private int maxLength;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Bank getBank() { return bank; }
    public void setBank(Bank bank) { this.bank = bank; }

    public AccountType getAccountType() { return accountType; }
    public void setAccountType(AccountType accountType) { this.accountType = accountType; }

    public int getMinLength() { return minLength; }
    public void setMinLength(int minLength) { this.minLength = minLength; }

    public int getMaxLength() { return maxLength; }
    public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
}
