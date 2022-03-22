package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {

    private Long userId; // to get the user ID
    private String fullName;
    private String phoneNumber;
    private String pinCode;
    private String address;
    private String landMark;
    private String city;
    private String state;
    private String type;
}
