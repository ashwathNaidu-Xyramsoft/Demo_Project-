package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.service.cartService.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bookstorecart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;


    @GetMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAllCartItems() {
        List<Cart> allCartItems = cartService.getAllCartItems();
        ArrayList<Book> arrayList = new ArrayList<>();
        for (int i = 0; i < allCartItems.size(); i++) {
            List<Book> bookList = allCartItems.get(i).getBooks();
            for (int j = 0; j < bookList.size(); j++) {
                Book book = bookList.get(j);
                arrayList.add(book);
            }
        }
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfull", arrayList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable("userId") Long userId) {
        List<Cart> cartData = cartService.getBookStoreDataByUserId(userId);
        ResponseDTO respDTO = new ResponseDTO("Get Call By Id Success", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
/*
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCartData(@Valid @RequestBody Book book) {
        Book bookData = cartService.addToCart(book.getBookId());
        ResponseDTO respDTO = new ResponseDTO(" book data created", bookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    */
/*

    @PostMapping("/create/{bookId}")
    public ResponseEntity<ResponseDTO> createCartData(@PathVariable Long bookId) {
        Cart cart = cartService.addToCart(bookId);
        ResponseDTO respDTO = new ResponseDTO(" Cart data created", cart);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
*/

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCartData(@RequestBody CartDTO cartDTO) {
        Cart cart = cartService.addDataToCart(cartDTO);
        ResponseDTO respDTO = new ResponseDTO(" Cart data created", cart);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
// addBooksToCartByCartID
    @PutMapping("/update/{cartId}")
    public ResponseEntity<ResponseDTO> updateBookStoreData(@PathVariable("cartId") Long cartId,@RequestParam Long quatity) {
        Cart cartData = cartService.updateCart(cartId, quatity);
        ResponseDTO respDTO = new ResponseDTO("updated adressBook data", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }


 // need to work

    @PutMapping("/update/addBooksToCartByCartID/{cartId}")
    public ResponseEntity<ResponseDTO> addBooksToCartByCartID(@PathVariable("cartId") Long cartId,@RequestParam Long bookId) {
        Cart cartData = cartService.addBooksToCartByCartID(cartId, bookId);
        ResponseDTO respDTO = new ResponseDTO("Book updated successfully in cart : ", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
// delete
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteBookStoreData(@PathVariable("cartId") Long cartId) {
        cartService.removeCart(cartId);
        ResponseDTO respDTO = new ResponseDTO("deleted sucessful ", "Deleted Id" + cartId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Get cart List for user
    @GetMapping(value = {"/get-All-Cart-Items-User"})
    public ResponseEntity<ResponseDTO> getAllCartItemsUser(@RequestParam String token) {
        List<Cart> allCartItems = cartService.getAllCartItemsUser(token);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfully", allCartItems);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Get cart List for user
    @GetMapping(value = {"/get-All-Carts-By-User-Id"})
    public ResponseEntity<ResponseDTO> findCartsByUsers_Id(@RequestParam Long userId) {
        List<Cart> allCartItems = cartService.findCartsByUsers_Id(userId);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfully", allCartItems);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // get Book Store Data By User Id
    @GetMapping(value = {"/getBookStoreDataByUserId"})
    public ResponseEntity<ResponseDTO> getBookStoreDataByUserId(@RequestParam Long userId) {
        List<Cart> allCartItems = cartService.getBookStoreDataByUserId(userId);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successfully", allCartItems);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}
