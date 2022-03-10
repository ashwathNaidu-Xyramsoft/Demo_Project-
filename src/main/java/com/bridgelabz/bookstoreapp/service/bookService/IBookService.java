package com.bridgelabz.bookstoreapp.service.bookService;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;

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
}
