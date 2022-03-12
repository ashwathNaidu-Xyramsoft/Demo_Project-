package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUsersId(Long userId);
    Cart getBookStoreDataByCartId(Long cartId);
    Cart deleteCartByCartId(Long cartId);
// same
    List<Cart> findCartsByUsersId(Long users_id);
// same
    List<Cart> findCartsByUsers_Id(Long users_id);

    List<Cart> getBookStoreDataByUsers_Id(Long users_id);

    List<Cart> getAllByUsers_Id(Long users_id);

    /*
    @Query(value = "select books_book_id from cart,cart_books where users_id = :users_id",nativeQuery = true)
    List<Book> getBooksByUser_Id_(Long userId);
    */
}
