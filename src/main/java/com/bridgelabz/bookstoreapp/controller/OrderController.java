package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Order;
import com.bridgelabz.bookstoreapp.service.orderService.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @GetMapping(value = {"","/","/get-all-order"})
    public ResponseEntity<ResponseDTO> getAllOrders(){
        List<Order> allOrders = orderServiceImpl.getAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", allOrders);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping( "/get-all-order-by-orderDTO")
    public ResponseEntity<ResponseDTO> getUserDataById(@RequestBody OrderDTO orderDTO){
        List<Order> orderList = orderServiceImpl.getAllOrders(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful at ORDER DTO: " , orderList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping( "/get-All-Orders-For-User")
    public ResponseEntity<ResponseDTO> getAllOrdersForUser(@RequestParam String token){
        List<Order> userOrderList = orderServiceImpl.getAllOrdersForUser(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful at ORDER DTO: " , userOrderList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/cancel-Orders-By-Id/{orderId}")
    public ResponseEntity<ResponseDTO> CancelOrdersById(@PathVariable Long orderId,@RequestParam String token){
        orderServiceImpl.cancelOrder(token, orderId);
        ResponseDTO responseDTO = new ResponseDTO("Canceled Order with id  " + orderId,"");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/place-Order")
    public ResponseEntity<ResponseDTO> addPersonData(@RequestParam String token){
        Order placeOrder = orderServiceImpl.placeOrder(token);
        ResponseDTO responseDTO = new ResponseDTO("Oder Place successfully : " ,placeOrder);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
