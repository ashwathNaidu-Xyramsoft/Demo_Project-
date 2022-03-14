package com.bridgelabz.bookstoreapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="FirstName",nullable=false)
    private String firstName;
    @Column(name="LastName",nullable=false)
    private String lastName;
    @Column(name="DateOfBirt")
    private String dateOfBirt;
    @Column(name="Email",nullable=false,unique=true)
    private String email;
    @Column(name="Password",nullable=false)
    private String password;
    @Column(name="PhoneNumber",nullable=false,unique=true)
    private String phoneNumber;
    @Column(name="KYC",unique=true)
    private String kyc;
    @Column(name="Role",nullable=false)
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy="users")
    private List<Cart> cart;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Order> orderList;
}