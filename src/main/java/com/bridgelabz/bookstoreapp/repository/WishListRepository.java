package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface WishListRepository extends JpaRepository<WishList,Long> {

    @Modifying
    @Transactional
    @Query(value = " delete from wish_list_books where books_book_id= :books_book_id",nativeQuery = true)
    void deleteBookByBook_Id(Long books_book_id);
}
