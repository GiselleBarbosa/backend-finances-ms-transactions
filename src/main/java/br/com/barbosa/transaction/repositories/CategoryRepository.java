package br.com.barbosa.transaction.repositories;

import br.com.barbosa.transaction.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
