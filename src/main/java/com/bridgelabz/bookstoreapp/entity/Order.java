package com.bridgelabz.bookstoreapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
@Table(name="orderTable")
@NoArgsConstructor
@AllArgsConstructor
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderId;

    private String orderDate = String.valueOf(LocalDate.now());

    private Long price;

    private Long quantity;

/*
    @Pattern(regexp = "[A-Za-z]{1}[a-zA-Z\\s]{2,}$", message = "address Invalid")
*/

    @OneToOne
    @JoinColumn ( name = "address_address_id" )
    private Address address;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToMany
    private List<Book> book; // will be stored

    private boolean cancel;
}