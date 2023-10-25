package org.acme.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Transfer {
    @NotBlank(message="From username is required")
    private String fromUsername;

    @NotBlank(message="To username is required")
    private String toUsername;

    @Min(message="Amount can not be negative", value=0)
    private Double amount;

    public Transfer() {
    }        

    public String getFromUsername() {
        return this.fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getToUsername() {
        return this.toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


}
