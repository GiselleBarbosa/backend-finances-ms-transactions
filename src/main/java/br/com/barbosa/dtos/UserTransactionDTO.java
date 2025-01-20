package br.com.barbosa.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserTransactionDTO {
    private String userId;
    private String userName;
    private String userEmail;
    private List<TransactionDTO> transactions;
}
