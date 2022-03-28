package com.bridgelabz.bookstoreapp.service.orderService;

import com.bridgelabz.bookstoreapp.entity.Address;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.entity.Order;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.repository.AddressRepository;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.service.cartService.ICartService;
import com.bridgelabz.bookstoreapp.service.userService.IUserLoginService;
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

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private IUserLoginService iUserLoginService;

    @Override // done
    public Order placeOrder(String token,Long price,Long quantity,Long address) {

        String emailId = UserLoginServiceImpl.verifyToken(token); // verify token and get email ID
        List<Book> booksByToken = iCartService.getBooksByToken(token); // getting books
        User user = userRepository.getUserByEmail(emailId); // get user from the email ID
        Address address1 = addressRepository.getAddressByAddressId(address);
        // need to write condition on QTY of BOOK and TOKEN ******* !!!

        Order order = new Order();
        order.setPrice(price); // get the total price after the QTY is final
        order.setQuantity(quantity); // set the final QTY
        order.setAddress(address1); // set the shipping address
        order.setUser(user); // set the user for particular User
        order.setBook(booksByToken); // set all th books
        return orderRepository.save(order); // saving the data in SQL DB
    }

    @Override
    public String sendOrderSummeryToEmail(String token) {
        String emailID = UserLoginServiceImpl.verifyToken(token);
        String subject="Order Summary";
        String message = getOrderSuccessfullyPage();
        String toEmail = emailID;
        String fromEmail= "ashwath.naidu@bridgelabz.com";
        UserLoginServiceImpl.sendEmail(subject, message,fromEmail,toEmail);
        return "Order successfully conformed";
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

    // html page for order successfully
    public static String getOrderSuccessfullyPage(){
        return
    "<body id='div-order-body' style=''>"+
        "<div id='div-order-logo-content'>" +
            "<img class='order-logo' src='https://th.bing.com/th/id/R.aeb1197df34e59e6b654fe8300a43871?rik=g%2f%2bCZ%2b5wg8zsJw&riu=http%3a%2f%2fwww.solidrockvideo.com%2fwp-content%2fuploads%2f2017%2f06%2fsuccess-icon-101-300x300.png&ehk=8Udh%2be74JWzOkSjTKEiMiGAAfX%2bUKsCijnp7s9cflrM%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1?auto=compress&cs=tinysrgb&w=50&h=50&dpr=2' style=' margin-top: 3%; margin-left: 40%;'>" +
            "<div id='order-name-message' style='position: absolute;  margin-top: -7%; margin-left: 35%; z-index: 1000;'>" +
                "<span class='order-message' style='font-size: 25px; font-style: Lato, Bold;'>Order Placed Successfully</span>" +
            "</div>"+
            "<div class='div-span-conformation'  style='margin-left: -6%;'>" +
                "<span id='span-conformation' style='margin-top: -0.5%; margin-top: 3%; margin-left: 40%; max-width: 20.5%; display: flex; text-align: center;'>hurray!!! your order is confirmed the order id is save the order id for further communication.</span>" +
            "</div>"+
                // table
            "<div id='table-container' style='margin-top: 20px;text-align: center;margin-left: 17%;'>" +
                "<table>" +
                    "<tr id='table-header' style='height: 42px; background-color: #DCDCDC; border: #333232;'>" +
                        "<th>Email ID</th>"+
                        "<th>Contact</th>"+
                        "<th>Address</th>"+
                    "</tr>"+
                    "<tr id='tables-body' style='border: #333232; height: 42px; background-color: #f7f1f1; color: #333232; padding: 10px;'>" +
                        "<td id='email-table' style=' text-decoration: none; min-width: 200px; height: 50px;'>ashwath.bly@gmail.com</td>" +
                        "<td id='contact-table' style='min-width: 200px; height: 50px;'>91 9008622627</td>" +
                        "<td id='address-table' style='max-width: 400px;' >Flat no -402,park view appartment, green graden layout," +
                        "Near sai baba temple road,munnekolala kundalahalli gate." +
                        "</td>"+
                    "</tr>" +
                "</table>" +
            "</div> " +
        "</div>"+
    "</body>";
    }
}
