package br.com.barbosa.configurations;

import br.com.barbosa.transaction.entities.Category;
import br.com.barbosa.transaction.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Bean
    CommandLineRunner init(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category("1", "Trabalho"));
                categoryRepository.save(new Category("2", "Moradia"));
                categoryRepository.save(new Category("3", "Alimentação"));
                categoryRepository.save(new Category("4", "Lazer"));
                categoryRepository.save(new Category("5", "Outros"));
            }
        };
    }
}
