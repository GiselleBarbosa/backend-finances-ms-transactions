package br.com.barbosa.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "A data da transação é obrigatória")
    private String date;
    @NotEmpty(message = "O titulo não pode ser vazio")
    private String title;

    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser maior que zero")
    private Double value;

    @NotEmpty(message = "O Tipo não pode ser vazio")
    private String type;

    @NotEmpty(message = "O ID da categoria não pode ser vazio")
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
