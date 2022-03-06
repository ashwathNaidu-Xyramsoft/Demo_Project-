package com.bridgelabz.bookstoreapp.service.bookService;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAllBook();
    Book getBookByID(Long bookId);
    Book addBook(BookDTO bookDTO);
    Book updateBook(Long bookID, BookDTO bookDTO);
    void deleteBook(Long bookId);

    Book changeBookQuantity(Long bookId, Long price);
    Book changeBookPrice(Long bookId, Long price);

}
