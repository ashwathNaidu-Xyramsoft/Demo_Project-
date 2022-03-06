package com.bridgelabz.bookstoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private User userList;

    @OneToMany(mappedBy="cart")
    private List<Book> bookId;

    private Long Quantity;

}
