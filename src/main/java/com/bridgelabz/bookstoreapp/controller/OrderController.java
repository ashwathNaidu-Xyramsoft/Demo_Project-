package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Order;
import com.bridgelabz.bookstoreapp.entity.UserLogin;
import com.bridgelabz.bookstoreapp.service.orderService.IOrderService;
import com.bridgelabz.bookstoreapp.service.orderService.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    IOrderService iOrderService;

    @GetMapping(value = {"","/","/get-all-order"}) // done
    public ResponseEntity<ResponseDTO> getAllOrders(){
        List<Order> allOrders = orderServiceImpl.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", allOrders);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping( "/getAllOrdersForUser/{token}") // done
    public ResponseEntity<ResponseDTO> getAllOrdersForUser(@PathVariable String token){
        List<Order> userOrderList = orderServiceImpl.getAllOrdersForUser(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful at ORDER DTO: " , userOrderList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/cancelOrdersByTokenAndId/{orderId}")
    public ResponseEntity<ResponseDTO> CancelOrdersById(@PathVariable Long orderId,@RequestParam String token){
        orderServiceImpl.cancelOrder(token, orderId);
        ResponseDTO responseDTO = new ResponseDTO("Canceled Order with id  " + orderId,"");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/placeOrder/{token}") // done
    public ResponseEntity<ResponseDTO> placeOrderByToken(@PathVariable String token,@RequestBody OrderDTO orderDTO){
        Order placeOrder = orderServiceImpl.placeOrder(token, orderDTO.getPrice(), orderDTO.getQuantity(), orderDTO.getAddress());
        ResponseDTO responseDTO = new ResponseDTO("Oder Place successfully : " ,placeOrder);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // send order summary to email
    @GetMapping("/sendOrderConformationToEmail/{token}")
    public ResponseEntity<ResponseDTO> sendOrderConformationToEmail(@PathVariable String token){
        String orderSummeryToEmail = iOrderService.sendOrderSummeryToEmail(token);
        ResponseDTO responseDTO = new ResponseDTO("sending Order Conformation To Email has been successfully sent : ",orderSummeryToEmail);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
