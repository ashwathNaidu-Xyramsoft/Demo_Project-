package com.bridgelabz.bookstoreapp.service.orderService;

import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Order;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.cartService.ICartService;
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

    @Autowired
    ICartService iCartService;

    @Override // done
    public Order placeOrder(String token,Long price,Long quantity,String address) {

        String emailId = UserLoginServiceImpl.verifyToken(token); // verify token and get email ID
        List<Book> booksByToken = iCartService.getBooksByToken(token); // getting books
        User user = userRepository.getUserByEmail(emailId); // get user from the email ID

        // need to write condition on QTY of BOOK and TOKEN ******* !!!

        Order order = new Order();
        order.setPrice(price); // get the total price after the QTY is final
        order.setQuantity(quantity); // set the final QTY
        order.setAddress(address); // set the shipping address
        order.setUser(user); // set the user for particular User
        order.setBook(booksByToken); // set all th books
        return orderRepository.save(order); // saving the data in SQL DB
    }

    @Override // need to test
    public String cancelOrder(String token, Long orderId) {
        String emailId = UserLoginServiceImpl.verifyToken(token);
        orderRepository.deleteById(orderId);
        return "Deleted by ID -> "+orderId;
    }

    @Override  // done
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override  // done
    public List<Order> getAllOrdersForUser(String token) {
        String emailID = UserLoginServiceImpl.verifyToken(token);
        User userByEmail = userRepository.getEmailIdByEmail(emailID);
        System.out.println("Its crossing this line");
        List<Order> ordersByUser_id = orderRepository.findOrdersByUser_Id(userByEmail.getId());
        return ordersByUser_id;
    }
}
