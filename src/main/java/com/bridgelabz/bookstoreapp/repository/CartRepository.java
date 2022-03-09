package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUsersId(Long userId);
    Cart getBookStoreDataByCartId(Long cartId);
    List<Cart> findCartsByUsersId(Long users_id);
    Cart deleteCartByCartId(Long cartId);
}
