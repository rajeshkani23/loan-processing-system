package com.rajeshkani.loan_processing_system.controller;

import com.rajeshkani.loan_processing_system.Exception.LoanNotFoundException;
import com.rajeshkani.loan_processing_system.model.Loan;
import com.rajeshkani.loan_processing_system.repository.LoanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private  final LoanRepository loanRepository;

    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    @PostMapping("/add")
    public ResponseEntity<String>addLoan(@RequestBody Loan loan){
        loanRepository.save(loan);

        return ResponseEntity.ok("Loan added successfully!") ;
    }
    @GetMapping("/high-value")
    public List<Loan>getHighValueLoans(){
        return loanRepository.findAll().stream()
                .filter(l -> l.getAmount()>100000)
                .collect(Collectors.toList());
    }
    @GetMapping("/unique-customers")
    public Set<String> getUniqueCustomers() {
        return loanRepository.findAll().stream()
                .map(Loan::getCustomerName)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable long id) {
        Loan loan = loanRepository.findAll().stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("Loan with ID " + id + " not found"));
        return ResponseEntity.ok(loan);
}
    @GetMapping("/group-by-type")
    public Map<String, List<Loan>> groupByLoanType() {
        return loanRepository.findAll().stream()
                .collect(Collectors.groupingBy(Loan::getLoanType));
    }

}
