package com.bridgelabz.bookstoreapp.service.orderService;

import com.bridgelabz.bookstoreapp.entity.Order;

import java.util.List;

public interface IOrderService {

    String cancelOrder(String token, Long orderId);
    List<Order> getAllOrders();
    List<Order> getAllOrdersForUser(String token);

    Order placeOrder(String token,Long priceLong,Long quantity,String Address);


    // need to send the mail after order placed

    }
