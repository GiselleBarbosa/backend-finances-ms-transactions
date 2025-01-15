package br.com.barbosa.entities;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Document(collection = "categories")
public class Category {

    @Id
    private final String id;

    private final String categoryName;

    public Category(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}


