package com.bridgelabz.bookstoreapp.service.bookService;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByID(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        log.debug("Book Data: "+ book.toString());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long bookId, BookDTO bookDTO) {
        Book book = this.getBookByID(bookId);
        modelMapper.map(bookDTO, book);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = this.getBookByID(bookId);
        bookRepository.delete(book);
    }

    @Override
    public Book changeBookQuantity(Long bookId, Long price) {
        return null;
    }

    @Override
    public Book changeBookPrice(Long bookId, Long price) {
        return null;
    }
}
