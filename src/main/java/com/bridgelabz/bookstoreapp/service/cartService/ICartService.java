package com.bridgelabz.bookstoreapp.service.cartService;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;

import java.util.List;

public interface ICartService {

    List<Cart> getAllCartItems();
    Book addToCart(BookDTO bookDTO);
    Cart getBookStoreDataById(Long cartId);
    void removeCart(Long cartId);
    Cart updateCart(Long catId, Long quantity);
    Cart getAllCartItemsUser(String Token); // option for now
}
