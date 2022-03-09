package com.bridgelabz.bookstoreapp.repository;


import com.bridgelabz.bookstoreapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookByBookId(Long bookId);
    Book findByBookId(Long bookId);

}
