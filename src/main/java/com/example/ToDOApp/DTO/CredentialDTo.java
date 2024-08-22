package com.example.ToDOApp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CredentialDTo(@JsonProperty("name") String name , String password) {
}
