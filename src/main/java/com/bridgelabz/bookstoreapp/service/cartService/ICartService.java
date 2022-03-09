package com.bridgelabz.bookstoreapp.service.cartService;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;

import java.util.List;

public interface ICartService {

    List<Cart> getAllCartItems();
    Cart addToCart(Long bookId);
    Cart addDataToCart(CartDTO cartDTO);
    Cart addBooksToCartByCartID(Long cartId,Long bookId);
    List<Cart>  getBookStoreDataByUserId(Long cartId);
    void removeCart(Long cartId);
    Cart updateCart(Long catId, Long quantity);
    List<Cart> getAllCartItemsUser(String Token); // option for now




}
