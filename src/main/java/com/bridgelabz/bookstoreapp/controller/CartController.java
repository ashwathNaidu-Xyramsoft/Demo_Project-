package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.service.cartService.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstorecart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAllCartItems() {
        List<Cart> allCartItems = cartService.getAllCartItems();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfull", allCartItems);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{cartId}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable("cartId") Long cartId) {
        Cart cartData = cartService.getBookStoreDataById(cartId);
        ResponseDTO respDTO = new ResponseDTO("Get Call By Id Success", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCartData(@Valid @RequestBody BookDTO bookDTO) {
        Book bookData = cartService.addToCart(bookDTO);
        ResponseDTO respDTO = new ResponseDTO(" book data created", bookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{personId}")
    public ResponseEntity<ResponseDTO> updateBookStoreData(@PathVariable("personId") Long cartId,
                                                           @Valid @RequestBody Long quatity) {
        Cart cartData = cartService.updateCart(cartId, quatity);
        ResponseDTO respDTO = new ResponseDTO("updated adressBook data", quatity);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteBookStoreData(@PathVariable("cartId") Long cartId) {
        cartService.removeCart(cartId);
        ResponseDTO respDTO = new ResponseDTO("deleted sucessful ", "Deleted Id" + cartId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}
