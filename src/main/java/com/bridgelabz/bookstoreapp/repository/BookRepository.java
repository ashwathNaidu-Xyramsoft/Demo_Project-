package com.bridgelabz.bookstoreapp.repository;


import com.bridgelabz.bookstoreapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.Pattern;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookByBookId(Long bookId);
    Book findByBookId(Long bookId);
    List<Book> getBooksByAuthor(@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid") String author);
    List<Book> getBooksByOrderByPriceAsc();
    List<Book> getBooksByOrderByPriceDesc();
    List<Book> getBooksByOrderByBookIdAsc();
    List<Book> getBooksByOrderByBookIdDesc();
}
