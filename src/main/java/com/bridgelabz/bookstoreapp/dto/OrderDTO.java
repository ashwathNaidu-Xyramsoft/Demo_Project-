package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class OrderDTO {

    @NotNull
    private String token;

    @NotNull
    private String orderDate;

    @NotNull
    private Long price;

    @NotNull
    private Long quantity;

    @Pattern(regexp = "[A-Za-z]{1}[a-zA-Z\\s]{2,}$", message = "Address is Invalid")
    private String address;

    private boolean cancel = false;
}
