package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class OrderDTO {

    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    @NotNull
    private String orderDate = myDateObj.format(myFormatObj);

    @NotNull
    private Long price;

    @NotNull
    private Long Quantity;

    @Pattern(regexp = "[A-Za-z]{1}[a-zA-Z\\s]{2,}$", message = "Address is Invalid")
    private String Address;

    @NotNull
    private boolean cancel = false;
}
