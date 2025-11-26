package com.example.demo.Controller.DTO;

import java.util.List;
import com.example.demo.Repository.Entity.Profile;

import jakarta.validation.constraints.*;

public record NewUserDTO(
        @Positive
        Integer createdBy,
        @NotBlank(message = "O nome não pode ser nulo ou vazio.") 
        String name,
        @NotBlank
        String handle,
        @NotBlank(message = "O email não pode ser nulo ou vazio.")
        @Email
        String email,
        @NotBlank
        @Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", 
                message = "A senha deve ter pelo menos 8 caracteres e conter pelo menos uma letra e um número")
        String password,
        String company,
        Profile.AccountType type,
        List<String> roles
)  {

}
