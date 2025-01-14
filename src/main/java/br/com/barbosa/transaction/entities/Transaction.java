package br.com.barbosa.transaction.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String date;
    private String title;
    private Double value;
    private String type;
    private String categoryId;
    private String categoryName;

    public Transaction() {
    }

    public Transaction(String id, String date, String title, Double value, String type, String categoryId, String categoryName) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.value = value;
        this.type = type;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
