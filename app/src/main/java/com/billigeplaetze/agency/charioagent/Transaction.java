package com.billigeplaetze.agency.charioagent;

public class Transaction {
    private TransactionId transactionId;

    private double donationAmount;
    private String taskDescription;
    private String photo;
    private String type;
    private String state;

    public TransactionId getTransactionId() {
        return transactionId;
    }

    public Transaction setTransactionId(TransactionId transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public double getDonationAmount() {
        return donationAmount;
    }

    public Transaction setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
        return this;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getPhoto() {
        return photo;
    }

    public Transaction setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Transaction setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
        return this;
    }

    public String getType() {
        return type;
    }

    public Transaction setType(String type) {
        this.type = type;
        return this;
    }

    public String getState() {
        return state;
    }

    public Transaction setState(String state) {
        this.state = state;
        return this;
    }

    public Transaction() {
    }

    public Transaction(TransactionId transactionId, double amount, String description, String type, String status) {
        this.transactionId = transactionId;
        this.donationAmount = amount;
        this.taskDescription = description;
        this.type = type;
        state = status;
    }

}
