package com.bridgelabz.bookstoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllEntity {

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "author name Invalid")
    private String author;
}
