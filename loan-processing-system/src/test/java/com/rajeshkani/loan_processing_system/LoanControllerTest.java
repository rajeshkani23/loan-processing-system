package com.rajeshkani.loan_processing_system;

import com.rajeshkani.loan_processing_system.controller.LoanController;
import com.rajeshkani.loan_processing_system.model.Loan;
import com.rajeshkani.loan_processing_system.repository.LoanRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanControllerTest {

    private LoanController controller;

    @BeforeEach
    void setup() {
        LoanRepository repository = new LoanRepository();
        controller = new LoanController(repository);

        // Add mock loan
        repository.save(new Loan(1, "Rajesh", "home", 250000, 720, LocalDate.now()));
        repository.save(new Loan(2, "Muthu", "auto", 95000, 690, LocalDate.now()));
    }

    @Test
    void testAddLoan() {
        Loan newLoan = new Loan(3, "Kumar", "personal", 150000, 700, LocalDate.now());
        ResponseEntity<String> response = controller.addLoan(newLoan);
        assertEquals("Loan added successfully!", response.getBody());
    }

    @Test
    void testGetHighValueLoans() {
        List<Loan> loans = controller.getHighValueLoans();
        assertFalse(loans.isEmpty());
        assertTrue(loans.get(0).getAmount() > 100000);
    }

    @Test
    void testGroupByLoanType() {
        var grouped = controller.groupByLoanType();
        assertTrue(grouped.containsKey("home"));
        assertTrue(grouped.containsKey("auto"));
    }

    @Test
    void testGetLoanById() {
        var response = controller.getLoanById(1L);
        assertEquals(1, response.getBody().getId());
    }
}
