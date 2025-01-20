package br.com.barbosa.services;

import br.com.barbosa.client.UserClient;
import br.com.barbosa.dtos.UserDTO;
import br.com.barbosa.entities.Category;
import br.com.barbosa.entities.Transaction;
import br.com.barbosa.exceptions.TransactionNotFoundException;
import br.com.barbosa.exceptions.TransactionValidationException;
import br.com.barbosa.repositories.CategoryRepository;
import br.com.barbosa.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserClient userClient;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(String id) {
        if (id == null) {
            throw new TransactionValidationException("O ID fornecido não deve ser nulo");
        }
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transação não encontrada para o ID: " + id));
    }

    public Transaction saveTransaction(Transaction transaction) {
        UserDTO user = userClient.getUserById(transaction.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado para o ID: " + transaction.getUserId());
        }

        if (transaction.getCategoryId() == null || transaction.getCategoryId().isEmpty()) {
            throw new TransactionValidationException("O ID da categoria fornecido não deve ser nulo ou vazio");
        }

        Category category = categoryRepository.findById(transaction.getCategoryId())
                .orElseThrow(() -> new TransactionNotFoundException("Categoria não encontrada para o ID: " + transaction.getCategoryId()));

        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(String id) {
        if (id == null) {
            throw new TransactionValidationException("O ID fornecido não deve ser nulo");
        }
        if (!transactionRepository.existsById(id)) {
            throw new TransactionNotFoundException("Transação não encontrada para o ID: " + id);
        }
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(String id, Transaction updatedTransaction) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transação não encontrada para o ID: " + id));

        existingTransaction.setDate(updatedTransaction.getDate());
        existingTransaction.setTitle(updatedTransaction.getTitle());
        existingTransaction.setValue(updatedTransaction.getValue());
        existingTransaction.setType(updatedTransaction.getType());

        if (updatedTransaction.getCategoryId() != null) {
            Category category = categoryRepository.findById(updatedTransaction.getCategoryId())
                    .orElseThrow(() -> new TransactionNotFoundException("Categoria não encontrada para o ID: " + updatedTransaction.getCategoryId()));
            existingTransaction.setCategory(category);
        }

        return transactionRepository.save(existingTransaction);
    }

    public List<Transaction> getTransactionsByUserId(String userId) {
        UserDTO user = userClient.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado para o ID: " + userId);
        }

        return transactionRepository.findByUserId(userId);
    }
}