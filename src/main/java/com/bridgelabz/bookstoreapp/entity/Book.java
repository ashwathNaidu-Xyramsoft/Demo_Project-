package com.bridgelabz.bookstoreapp.entity;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long bookId;
    
    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    @Column(nullable = false,name="Name")
    private String name;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    @Column(nullable = false,name="Author")
    private String author;

    @Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid")
    @Column(nullable = false,name="Description")
    private String description;

    @Column(nullable = false,name="BookLogo")
    private String bookLogo;

    @Column(nullable = false,name="Price")
    private Long price;

    @Column(nullable = false,name="Quantity")
    private Long quantity;

    @ManyToOne
    private Cart cart;

    @ManyToMany(mappedBy="book")
    private List<Order> order;

    public Book(BookDTO bookDTO) {

    }
}

