package br.com.barbosa.repositories;

import br.com.barbosa.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
