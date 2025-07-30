package com.rajeshkani.loan_processing_system.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Loan {
    private long id;
    private String customerName;
    private String loanType;
    private double amount;
    private int creditScore;

    public Loan(long id, String customerName, String loanType, double amount, int creditScore, LocalDate appliedDate) {
        this.id = id;
        this.customerName = customerName;
        this.loanType = loanType;
        this.amount = amount;
        this.creditScore = creditScore;
        this.appliedDate = appliedDate;
    }

    private LocalDate appliedDate;
}
