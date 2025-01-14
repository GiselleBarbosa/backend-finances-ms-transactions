package br.com.barbosa.transaction.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
public class Category {
    @Id
    private String id;
    private String categoryName;

    public Category() {
    }

    public Category(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

}


