package br.com.barbosa.transaction.entities;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private String date;
    private String title;
    private Double value;
    private String type;
    private String categoryId;

    @DBRef
    private Category category;

    public Transaction() {
    }

    public Transaction(String id, String date, String title, Double value, String type, String categoryId, String categoryName) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.value = value;
        this.type = type;
        this.categoryId = categoryId;
    }

}
