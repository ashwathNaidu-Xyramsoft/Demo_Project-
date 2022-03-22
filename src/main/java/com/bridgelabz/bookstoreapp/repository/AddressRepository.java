package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> getAddressesByUserId(Long user_id);
    Address getAddressByAddressId(Long addressId);
}
