package com.bridgelabz.bookstoreapp.service.bookService;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;

import javax.validation.constraints.Pattern;
import java.util.List;

public interface IBookService {

    List<Book> getAllBook(String token);
    Book getBookByID(Long bookId);
    Book addBook(String token, BookDTO bookDTO);
    Book updateBook(String token, Long bookID, BookDTO bookDTO);
    void deleteBook(String token, Long bookId);

    Book changeBookQuantity(String token,Long bookId, Long quantity);
    Book changeBookPrice(String token, Long bookId, Long price);

    List<Book> getAllBookNew();

    List<Book> getBooksByOrderByPriceAsc();
    List<Book> getBooksByOrderByPriceDesc();
    List<Book> getBooksByOrderByBookIdAsc();
    List<Book> getBooksByOrderByBookIdDesc();
    List<Book> getBooksByAuthor(@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid") String author);
}
