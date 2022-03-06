package com.bridgelabz.bookstoreapp.service.userService;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    User getUserById(Long userId);
    User addUser(UserDTO userDTO);
    User updateUser(Long userId, UserDTO userDTO);
    String deleteUser(Long userId);
}
