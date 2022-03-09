package com.bridgelabz.bookstoreapp.service.orderService;

import com.bridgelabz.bookstoreapp.entity.Order;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Order placeOrder(String token) {
       // String emailId = UserLoginServiceImpl.verifyToken(token);

        return null;
    }

    @Override
    public String cancelOrder(String token, Long orderId) {
        orderRepository.deleteById(orderId);
        return "Deleted ";
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersForUser(String token) {
        return null;
    }
}
