package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.service.wishList.IWishListService;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.WishListDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.WishList;
import com.bridgelabz.bookstoreapp.service.cartService.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("/wishList/")
public class WishListController {

    @Autowired
    private IWishListService iWishListService;

    @Autowired
    private ICartService iCartService;

    // get Books By Token
    @GetMapping(value = {"/getWishListBooksByToken/{token}"})
    public ResponseEntity<ResponseDTO> getBooksByToken(@PathVariable String token) {
        System.out.println(token);
        List<Book> booksByToken = iWishListService.getBooksByToken(token);
        ResponseDTO respDTO = new ResponseDTO("Get cartId Call Successfully", booksByToken);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping ("create") // done
    public ResponseEntity<ResponseDTO> createWishListData(@RequestBody WishListDTO wishListDTO) {
        WishList toWishList = iWishListService.addBookToWishList(wishListDTO);
        ResponseDTO respDTO = new ResponseDTO(" Book added to wishlist successfully", toWishList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // delete
    @Transactional // done
    @DeleteMapping("deleteBookByBook_Id/{books_book_id}")
    public ResponseEntity<ResponseDTO> deleteBookFromWishListBook_Id(@PathVariable("books_book_id") Long books_book_id) {
        System.out.println(books_book_id);
        String deletedBook = iWishListService.deleteBookByBook_Id(books_book_id);
        ResponseDTO respDTO = new ResponseDTO("deleted successful ", deletedBook);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}
