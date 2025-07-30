package com.rajeshkani.loan_processing_system.repository;

import com.rajeshkani.loan_processing_system.model.Loan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanRepository {
    private  final List<Loan>loans=new ArrayList<>();
    public void save(Loan loan) {
        loans.add(loan);
    }

    public List<Loan> findAll() {
        return loans;
    }
}
