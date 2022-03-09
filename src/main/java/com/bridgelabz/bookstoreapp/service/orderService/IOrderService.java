package com.bridgelabz.bookstoreapp.service.orderService;

import com.bridgelabz.bookstoreapp.entity.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(String token);
    String cancelOrder(String token, Long orderId);
    List<Order> getAllOrders();
    List<Order> getAllOrdersForUser(String token);
}
