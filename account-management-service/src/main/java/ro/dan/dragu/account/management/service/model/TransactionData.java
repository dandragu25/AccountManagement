package ro.dan.dragu.account.management.service.model;

import java.time.LocalDateTime;

public class TransactionData {

    private final Long id;
    private final Double amount;
    private final String transactionDate;
    private final String details;

    public TransactionData(Long id, Double amount, String transactionDate, String details) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getDetails() {
        return details;
    }
}
