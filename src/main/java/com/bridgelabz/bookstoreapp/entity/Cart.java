package com.bridgelabz.bookstoreapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    private User users;

    @JsonIgnore
    @OneToMany(mappedBy="cart",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Book> books;

    private Long Quantity;

    // add books into list
    public void addBookToCart(Book bookByBookId) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(bookByBookId); // adding the books
        bookByBookId.setCart(this); // connecting
    }
}
