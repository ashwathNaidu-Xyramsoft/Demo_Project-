package com.bridgelabz.bookstoreapp.service.cartService;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.entity.User;

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



    List<Cart> findCartsByUsers_Id(Long userId);

    List<Cart> getBookStoreDataByUsers_Id(Long users_id);

    Long getUserByEmail(String token);

    String deleteBookByBook_Id(Long books_book_id);






}
