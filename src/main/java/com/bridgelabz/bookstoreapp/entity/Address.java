package com.bridgelabz.bookstoreapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long addressId;

    private String fullName;
    private String phoneNumber;
    private String pinCode;
    private String address;
    private String landMark;
    private String city;
    private String state;
    private String type;

    @ManyToOne
    private User user;

}
