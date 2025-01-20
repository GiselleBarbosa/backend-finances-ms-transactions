package br.com.barbosa.dtos;

import lombok.Data;

@Data
public class TransactionDTO {
    private String id;
    private String date;
    private String title;
    private Double value;
    private String type;
}
