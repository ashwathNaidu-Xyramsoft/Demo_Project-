package com.bridgelabz.bookstoreapp.service.userService;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers(String token);
    User getUserById(String token, Long userId);
    User addUser(UserDTO userDTO);
    User updateUser(String token, Long userId, UserDTO userDTO);
    String deleteUser(String token, Long userId);
}
