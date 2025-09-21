package com.daggiizar.clients.dto;

import java.util.UUID;

public class AccountResponseDto {
    private UUID id;
    private String bank;
    private String accountType;
    private String accountNumber;
    private String phoneNumber;
    private String accountName;
    private String holderDocType;
    private String holderDocNumber;

    public UUID getId() { return id; } public void setId(UUID id) { this.id = id; }
    public String getBank() { return bank; } public void setBank(String bank) { this.bank = bank; }
    public String getAccountType() { return accountType; } public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getAccountNumber() { return accountNumber; } public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getPhoneNumber() { return phoneNumber; } public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAccountName() { return accountName; } public void setAccountName(String accountName) { this.accountName = accountName; }
    public String getHolderDocType() { return holderDocType; } public void setHolderDocType(String holderDocType) { this.holderDocType = holderDocType; }
    public String getHolderDocNumber() { return holderDocNumber; } public void setHolderDocNumber(String holderDocNumber) { this.holderDocNumber = holderDocNumber; }
}
