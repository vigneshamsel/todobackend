package com.example.ToDOApp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CredentialDTo(@JsonProperty("username") String name , String password) {
}
