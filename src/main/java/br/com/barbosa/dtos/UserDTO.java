package br.com.barbosa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {
    @JsonProperty("id")
    private String userId;

    @JsonProperty("name")
    private String userName;

    @JsonProperty("email")
    private String userEmail;
}