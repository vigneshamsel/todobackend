package com.example.ToDOApp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CredentialDTo(@JsonProperty("name") @NotBlank(message = "Name must not be empty") String name,
                            @NotBlank(message = "Password must not be empty") String password) {
}
