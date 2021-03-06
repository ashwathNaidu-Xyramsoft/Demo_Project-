package com.bridgelabz.bookstoreapp.service.bookService;

import com.bridgelabz.bookstoreapp.service.userService.UserLoginServiceImpl;
import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
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

    @Override // i need to use
    public List<Book> getAllBook(String token) {
        UserLoginServiceImpl.verifyToken(token);
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByID(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public Book addBook(String token, BookDTO bookDTO) {
        UserLoginServiceImpl.verifyToken(token);
        Book book = modelMapper.map(bookDTO, Book.class);
        log.debug("Book Data: "+ book.toString());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(String token, Long bookId, BookDTO bookDTO) {
        UserLoginServiceImpl.verifyToken(token);
        Book book = this.getBookByID(bookId);
        modelMapper.map(bookDTO, book);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String token, Long bookId) {
        UserLoginServiceImpl.verifyToken(token);
        Book book = this.getBookByID(bookId);
        bookRepository.delete(book);
    }

    @Override
    public Book changeBookQuantity(String token ,Long bookId, Long quantity) {
        UserLoginServiceImpl.verifyToken(token);
        Book book = bookRepository.findByBookId(bookId);
        if (book != null){
            book.setQuantity(quantity);
            return bookRepository.save(book);
        }
        throw new BookStoreException("Book not found !!!! and enter the valid book ID ");
    }

    @Override
    public Book changeBookPrice(String token, Long bookId, Long price) {
        UserLoginServiceImpl.verifyToken(token);
        Book book = bookRepository.findByBookId(bookId);
        if (book != null){
            book.setPrice(price);
            return bookRepository.save(book);
        }
        throw new BookStoreException("Book not found !!!! and enter the valid book ID ");
    }

    @Override
    public List<Book> getAllBookNew() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByOrderByPriceAsc() {
        return bookRepository.getBooksByOrderByPriceAsc();
    }

    @Override
    public List<Book> getBooksByOrderByPriceDesc() {
        return bookRepository.getBooksByOrderByPriceDesc();
    }

    @Override
    public List<Book> getBooksByOrderByBookIdAsc() {
        return bookRepository.getBooksByOrderByBookIdAsc();
    }

    @Override
    public List<Book> getBooksByOrderByBookIdDesc() {
        return bookRepository.getBooksByOrderByBookIdDesc();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    @Override// not used
    public List<Book> getBooksByAuthorOrName(String author, String name) {
        return bookRepository.getBooksByAuthorOrName(author,name);
    }
}
