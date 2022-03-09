package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class BookDTO {

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    @Column(nullable = false, name = "Name")
    private String name;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    @Column(nullable = false, name = "Name")
    private String author;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    @Column(nullable = false, name = "Description")
    private String description;

    @Column(nullable = false, name = "BookLogo")
    private String bookLogo;

    @Column(nullable = false, name = "Price")
    private Long price;

    @Column(nullable = false, name = "Quantity")
    private Long quantity;
}

/*
package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class BookDTO {

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    private String name;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "author first name Invalid")
    private String author;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "description first name Invalid")
    private String description;

    private String bookLogo;

    private Long price;

    private Long quantity;
}
*/
