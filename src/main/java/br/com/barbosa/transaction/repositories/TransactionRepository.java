package br.com.barbosa.transaction.repositories;

import br.com.barbosa.transaction.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
