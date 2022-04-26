package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.service.AddressService.AddressServiceImpl;
import com.bridgelabz.bookstoreapp.dto.AddressDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping (value="address")
public class AddressController {
    
    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @GetMapping (value = {"/getAddressByToken/{token}"})
    public ResponseEntity<ResponseDTO> getAddressByToken(@PathVariable String token){
        List<Address> addresses = addressServiceImpl.getAddressByToken(token);
        ResponseDTO booksResponseDTO = new ResponseDTO("Get All address Successfully", addresses);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addAddressByToken(@RequestBody AddressDTO addressDTO){
        Address address = addressServiceImpl.addAddress(addressDTO);
        ResponseDTO bookResponseDTO = new ResponseDTO("Created address Successfully", address);
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);
    }


}