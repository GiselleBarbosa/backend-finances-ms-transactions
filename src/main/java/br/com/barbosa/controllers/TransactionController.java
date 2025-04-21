package br.com.barbosa.controllers;

import br.com.barbosa.client.UserClient;
import br.com.barbosa.configurations.DataInitializer;
import br.com.barbosa.dtos.TransactionDTO;
import br.com.barbosa.dtos.UserDTO;
import br.com.barbosa.dtos.UserTransactionDTO;
import br.com.barbosa.entities.Transaction;
import br.com.barbosa.services.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "https://finances-fawn.vercel.app", allowedHeaders = "Content-Type")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserClient userClient;

    Logger logger = LoggerFactory.getLogger(DataInitializer.class);

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

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserTransactionDTO> getTransactionsAndUser(@PathVariable String userId) {
        UserDTO user = userClient.getUserById(userId);
        logger.info("Usuário: {}", user);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(transaction -> {
                    TransactionDTO dto = new TransactionDTO();
                    dto.setId(transaction.getId());
                    dto.setDate(transaction.getDate());
                    dto.setTitle(transaction.getTitle());
                    dto.setValue(transaction.getValue());
                    dto.setType(transaction.getType());

                    logger.info("Transação: {}", dto);

                    return dto;
                })
                .toList();

        UserTransactionDTO response = new UserTransactionDTO();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setUserEmail(user.getUserEmail());
        response.setTransactions(transactionDTOs);

        logger.info("Resposta: {}", response);

        return ResponseEntity.ok(response);
    }

}

