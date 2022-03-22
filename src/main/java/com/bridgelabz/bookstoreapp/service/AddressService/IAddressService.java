package com.bridgelabz.bookstoreapp.service.AddressService;

import com.bridgelabz.bookstoreapp.dto.AddressDTO;
import com.bridgelabz.bookstoreapp.entity.Address;

import java.util.List;

public interface IAddressService {
    List<Address> getAddressByToken(String token);
    Address addAddress(AddressDTO addressDTO);
    Address updateAddress(AddressDTO addressDTO);
    Void deleteAddressByID();
}
