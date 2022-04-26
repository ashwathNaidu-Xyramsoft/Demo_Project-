package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.service.bookService.IBookService;
import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="bookstore")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping(value = {"", "/","/get"})
    public ResponseEntity<ResponseDTO> getAllBooks(){
        List<Book> books = bookService.getAllBookNew();
        ResponseDTO booksResponseDTO = new ResponseDTO("Get All Books Successfully", books);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("bookId") long bookId){
        Book book = bookService.getBookByID(bookId);
        ResponseDTO booksResponseDTO = new ResponseDTO("Found book Successfully having id: "
                +bookId, book);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addBookData(@RequestParam String token,@Valid @RequestBody BookDTO bookDTO){
        Book book = bookService.addBook(token,bookDTO);
        ResponseDTO bookResponseDTO = new ResponseDTO("Created Book Successfully", book);
        return new ResponseEntity<ResponseDTO>(bookResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookDataById(@RequestParam String token,@PathVariable("bookId") long bookId, @Valid @RequestBody BookDTO bookDTO){
        Book book = bookService.updateBook(token,bookId, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book with id  " + bookId, book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deletePersonDataById(@RequestParam String token,@PathVariable("bookId") long bookId){
        bookService.deleteBook(token,bookId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully ", "Book with id: "+bookId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    //changeBookPrice
    @PutMapping("/update/changeBookPrice")
    public ResponseEntity<ResponseDTO> changeBookPrice(@RequestParam String token,@RequestParam Long bookId,@RequestParam Long price){
        Book book = bookService.changeBookPrice(token,bookId,price);
        ResponseDTO responseDTO = new ResponseDTO("Book price successfully updated with id  " + bookId, book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //changeBookQuantity
    @PutMapping("/update/changeBookQuantity")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@RequestParam String token, @RequestParam Long bookId,@RequestParam Long quantity){
        Book book = bookService.changeBookQuantity(token,bookId,quantity);
        ResponseDTO responseDTO = new ResponseDTO("Book price successfully updated with id  " + bookId, book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

// SORTING
    @GetMapping(value = {"/getBooksByOrderByPriceAsc"})
    public ResponseEntity<ResponseDTO> getBooksByOrderByPriceAsc(){
        List<Book> books = bookService.getBooksByOrderByPriceAsc();
        ResponseDTO booksResponseDTO = new ResponseDTO("Get All Books ASC ORDER Successfully", books);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

    // SORTING
    @GetMapping(value = {"/getBooksByOrderByPriceDesc"})
    public ResponseEntity<ResponseDTO> getBooksByOrderByPriceDesc(){
        List<Book> books = bookService.getBooksByOrderByPriceDesc();
        ResponseDTO booksResponseDTO = new ResponseDTO("Get All Books DESC ORDER Successfully", books);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

    // BOOKS SORTING
    @GetMapping(value = {"/getBooksByOrderByBookIdAsc"})
    public ResponseEntity<ResponseDTO> getBooksByOrderByBookIdAsc(){
        List<Book> books = bookService.getBooksByOrderByBookIdAsc();
        ResponseDTO booksResponseDTO = new ResponseDTO("Get All Books ASC ORDER Successfully", books);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

    // BOOKS SORTING
    @GetMapping(value = {"/getBooksByOrderByBookIdDesc"})
    public ResponseEntity<ResponseDTO> getBooksByOrderByBookIdDesc(){
        List<Book> books = bookService.getBooksByOrderByBookIdDesc();
        ResponseDTO booksResponseDTO = new ResponseDTO("Get All Books DESC ORDER Successfully", books);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

    // BOOKS searchBooksBy Author
    @PostMapping(value = {"/getBooksByAuthor"})
    public ResponseEntity<ResponseDTO> getBooksByAuthor(@RequestBody BookDTO  bookDTO){
        List<Book> booksByAuthor = bookService.getBooksByAuthor(bookDTO.getAuthor());
        ResponseDTO booksResponseDTO = new ResponseDTO("Get all books by name successfully", booksByAuthor);
        return new ResponseEntity<ResponseDTO>(booksResponseDTO, HttpStatus.OK);
    }

}