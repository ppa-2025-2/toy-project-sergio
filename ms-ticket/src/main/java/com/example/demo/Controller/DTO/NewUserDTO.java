package com.example.demo.Controller.DTO;

import java.util.List;

import com.example.demo.Repository.Entity.Profile;

public record NewUserDTO(
        String name,
        String handle,
        String email,
        String password,
        String company,
        Profile.AccountType type,
        List<String> roles
)  {

}
