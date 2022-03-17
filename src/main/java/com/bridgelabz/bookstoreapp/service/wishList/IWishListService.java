package com.bridgelabz.bookstoreapp.service.wishList;

import com.bridgelabz.bookstoreapp.dto.WishListDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.entity.WishList;

import java.util.List;

public interface IWishListService {
    Long getUserByEmail(String token); // can also call that ICartService



    List<Book> getBooksByToken(String token);



    WishList addBookToWishList(WishListDTO wishListDTO);
    Cart addBooksToCartByCartID(Long cartId, Long bookId, Long quantity); // not needed (optional)


    String deleteBookByBook_Id(Long books_book_id);

}
