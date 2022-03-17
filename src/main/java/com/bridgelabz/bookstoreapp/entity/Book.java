package com.bridgelabz.bookstoreapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    /* @JoinTable(name ="book_cart_table",joinColumns = {@JoinColumn(name="cart_id",referencedColumnName = "bookId")},
            inverseJoinColumns = {@JoinColumn(name ="book_id",referencedColumnName = "cartId")})*/
    /*@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "My_BOOK_CART_TABLE",
            joinColumns = {@JoinColumn(name = "BOOK_FK", insertable = false,
                    updatable = false, referencedColumnName = "bookId")},
            inverseJoinColumns = {@JoinColumn(name = "CART_FK", insertable = false,
                    updatable = false, referencedColumnName = "cartId")}
    )   // many to many*/
    @JsonIgnore
    @ManyToMany(mappedBy ="books",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Cart> cart;

    @ManyToMany(mappedBy="book")
    private List<Order> order;

    // new added

    @JsonIgnore
    @ManyToMany(mappedBy ="books",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<WishList> wishList;

}