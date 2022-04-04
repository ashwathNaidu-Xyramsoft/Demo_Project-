package com.bridgelabz.bookstoreapp.service.userService;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.entity.User;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers(String token) {
        UserLoginServiceImpl.verifyToken(token);
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String token, Long userId) {
        UserLoginServiceImpl.verifyToken(token);
        return userRepository.findById(userId).stream().filter(userData -> userData.getId()==userId).findFirst().orElseThrow(()->new BookStoreException("User not found, enter the valid User ID"));
    }

    @Override
    public User addUser(UserDTO userDTO) {
        String userEncodePassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(userEncodePassword);
        User userData = modelMapper.map(userDTO, User.class);
        log.info("User data : ",userData);
        return userRepository.save(userData);
    }

    @Override
    public User updateUser(String token, Long userId, UserDTO userDTO) {
        UserLoginServiceImpl.verifyToken(token);
        return null;
    }

    @Override
    public String deleteUser(String token, Long userId) {
        UserLoginServiceImpl.verifyToken(token);
        userRepository.deleteById(userId);
        log.info("User data successfully deleted by ID -> ",userId);
        return "User data successfully deleted by ID -> " + userId;
    }

}
