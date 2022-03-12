package com.bridgelabz.bookstoreapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long userId; // token
    private Long bookId; // we are getting book id
    private Long quantity; // any number
    private Long cartId;
}

