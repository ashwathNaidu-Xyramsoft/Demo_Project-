package com.bridgelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class WishListDTO {
        private Long userId; // token
        private Long bookId; // we are getting book id
        private Long wishListId;
    }

