package br.com.barbosa.transaction.services;

import br.com.barbosa.transaction.entities.Category;
import br.com.barbosa.transaction.entities.Transaction;
import br.com.barbosa.transaction.repositories.CategoryRepository;
import br.com.barbosa.transaction.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public Transaction saveTransaction(Transaction transaction) {
        if (transaction.getCategoryId() == null || transaction.getCategoryId().isEmpty()) {
            throw new IllegalArgumentException("The given category id must not be null or empty");
        }

        Category category = categoryRepository.findById(transaction.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(String id) {
        if (id == null) {
            throw new IllegalArgumentException("O id fornecido não deve ser nulo");
        }
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(String id, Transaction updatedTransaction) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        existingTransaction.setDate(updatedTransaction.getDate());
        existingTransaction.setTitle(updatedTransaction.getTitle());
        existingTransaction.setValue(updatedTransaction.getValue());
        existingTransaction.setType(updatedTransaction.getType());

        if (updatedTransaction.getCategoryId() != null) {
            Category category = categoryRepository.findById(updatedTransaction.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            existingTransaction.setCategory(category);
        }

        return transactionRepository.save(existingTransaction);
    }
}
