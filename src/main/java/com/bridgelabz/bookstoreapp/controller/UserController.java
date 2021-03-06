package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.service.userService.UserServiceImpl;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping(value = {"","/","/get"})
    public ResponseEntity<ResponseDTO> getAllUsers(@RequestParam String token){
        List<User> allUsers = userServiceImpl.getAllUsers(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", allUsers);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping( "/getBYId/{userId}")
    public ResponseEntity<ResponseDTO> getUserDataById(@RequestParam String token,@PathVariable long userId){
        User userById = userServiceImpl.getUserById(token,userId);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successful at ID: " + userId, userById);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addUserData(@Valid @RequestBody UserDTO userDTO){
        User user = userServiceImpl.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Create Person Data for: " ,user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updateUserById( @RequestParam String token,@PathVariable long userId,@Valid @RequestBody UserDTO userDTO){
        User user = userServiceImpl.updateUser(token,userId, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Person with id  " + userId, user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDTO> deleteUserById(@RequestParam String token,@PathVariable long userId){
        userServiceImpl.deleteUser(token,userId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted User with id  " + userId,"");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

}
