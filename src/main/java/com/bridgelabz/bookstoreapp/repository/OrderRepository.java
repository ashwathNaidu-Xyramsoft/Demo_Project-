package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrdersByUser_Id(Long user_id);

    // select * from order_table where user_id=1 and order_date='2022-03-15';

    Order findByUser_IdAndOrderDate(Long user_id, String orderDate);
}
