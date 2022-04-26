package com.bridgelabz.bookstoreapp.repository;


import com.bridgelabz.bookstoreapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Pattern;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookByBookId(Long bookId);
    Book findByBookId(Long bookId);

    // not using
    List<Book> getBooksByAuthorOrName(@Pattern ( regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid" ) String author, @Pattern ( regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid" ) String name);

    List<Book> getBooksByAuthor(@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Person first name Invalid") String author);
    List<Book> getBooksByOrderByPriceAsc();
    List<Book> getBooksByOrderByPriceDesc();
    List<Book> getBooksByOrderByBookIdAsc();
    List<Book> getBooksByOrderByBookIdDesc();
}
