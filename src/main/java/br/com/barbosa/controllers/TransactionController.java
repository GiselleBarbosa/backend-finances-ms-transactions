package br.com.barbosa.controllers;

import br.com.barbosa.entities.Transaction;
import br.com.barbosa.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable String id,
            @Valid @RequestBody Transaction updatedTransaction) {
        Transaction transaction = transactionService.updateTransaction(id, updatedTransaction);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}

