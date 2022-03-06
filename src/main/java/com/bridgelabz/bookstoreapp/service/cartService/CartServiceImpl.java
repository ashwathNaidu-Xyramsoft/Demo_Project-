package com.bridgelabz.bookstoreapp.service.cartService;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements ICartService{

    @Autowired
    private CartRepository cartrepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Cart> getAllCartItems() {
        return cartrepository.findAll();
    }


    public Cart getBookStoreDataById(Long cartId) {
        return cartrepository.findById(cartId).orElseThrow(() -> new BookStoreException("Cart Not Found!!"));
    }

    @Override
    public Book addToCart(BookDTO bookDTO) {
        // Book bookData = new Book(bookDTO); // using model mapper
        Book bookData = modelMapper.map(bookDTO, Book.class);
        log.debug("Book Data: "+bookData.toString());
        return bookRepository.save(bookData);
    }

    @Override
    public void removeCart(Long bookId) {
        Cart cartData = this.getBookStoreDataById(bookId);
        cartrepository.delete(cartData);
    }

    @Override
    public Cart updateCart(Long cartId, Long quantity) {
        Cart cartData = this.getBookStoreDataById(cartId);
        modelMapper.map(quantity,cartData);
        return cartrepository.save(cartData);
    }

    @Override
    public Cart getAllCartItemsUser(String token) {
        return null;
    }
}
