package com.bridgelabz.bookstoreapp.service.orderService;

import com.bridgelabz.bookstoreapp.entity.Order;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.userService.UserLoginServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Order placeOrder(String token) {
        String emailId = UserLoginServiceImpl.verifyToken(token);
        return null;
    }

    @Override // need to test
    public String cancelOrder(String token, Long orderId) {
        UserLoginServiceImpl.verifyToken(token);
        orderRepository.deleteById(orderId);
        return "Deleted by ID -> "+orderId;
    }

    @Override  // need to test
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override  // need to test
    public List<Order> getAllOrdersForUser(String token) {
        String emailID = UserLoginServiceImpl.verifyToken(token);
        User userByEmail = userRepository.getEmailIdByEmail(emailID);
        System.out.println("Its crossing this line");
        List<Order> ordersByUser_id = orderRepository.findOrdersByUser_Id(userByEmail.getId());
        return ordersByUser_id;
    }
}
