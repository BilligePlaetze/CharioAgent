package com.billigeplaetze.agency.charioagent;

public class Transaction {
    String agentId;
    String transactionCode;
    double amount;
    String description;
    byte[] photo;
    String Type;
    String Status;

    public Transaction() {
    }

    public Transaction(String agentId, String transactionCode, double amount, String description, String type, String status) {
        this.agentId = agentId;
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.description = description;
        Type = type;
        Status = status;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
