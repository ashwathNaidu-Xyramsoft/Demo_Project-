package com.bridgelabz.bookstoreapp.repository;


import com.bridgelabz.bookstoreapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
