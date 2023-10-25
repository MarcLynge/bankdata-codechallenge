package org.acme.model;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Account {
    @Schema(hidden = true)
    private UUID id;

    @NotBlank(message="Username must be set")
    private String username;
    private String name;

    @Min(message="Balance can not be negative", value=0)
    private Double balance;

    @NotBlank(message="Password must be set")
    private String password;
    
    public Account() {
    }

    public UUID  getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
