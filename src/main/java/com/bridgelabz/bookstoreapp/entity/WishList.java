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
public class WishList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long wishListId;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn
    private List<Book> books; // will be stored

    // add books into list
    public void addBookToWishList(Book bookByBookId) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(bookByBookId); // adding the books
    }

}
