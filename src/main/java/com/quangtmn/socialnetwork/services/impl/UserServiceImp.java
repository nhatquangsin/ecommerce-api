package com.quangtmn.socialnetwork.services.impl;

import com.quangtmn.socialnetwork.entities.User;
import com.quangtmn.socialnetwork.exceptions.KeyDuplicateException;
import com.quangtmn.socialnetwork.exceptions.UserNotFoundException;
import com.quangtmn.socialnetwork.messages.Message;
import com.quangtmn.socialnetwork.repositories.UserRepository;
import com.quangtmn.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("There is no record");
        } else {
            return users;
        }
    }

    @Override
    public boolean insertUser(User user) {
        Optional<User> checkUser = userRepository.findById(user.getId());
        User checkUsername = userRepository.getUserByUsername(user.getUsername());
        if (checkUser.isPresent()) {
            throw new KeyDuplicateException("User id exist");
        }
        if (checkUsername != null) {
            throw new KeyDuplicateException("Username is exist");
        }
        user.setEnabled(1);
        user.setRole("admin");
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("Username is not exist");
        }
    }

    @Override
    public void updateById(int id, User user) {
        Optional<User> existUser = userRepository.findById(id);
        if (existUser.isPresent()) {
            if (user.getUsername() != null) existUser.get().setUsername(user.getUsername());
            if (user.getPassword() != null) existUser.get().setPassword(user.getPassword());
            if (user.getEmail() != null) existUser.get().setEmail(user.getEmail());
            userRepository.save(existUser.get());
        } else {
            throw new UserNotFoundException(Message.USER_NOT_FOUND);
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Message.USER_NOT_FOUND);
        }
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user != null) {
            return user.get();
        }
        throw new UserNotFoundException(Message.USER_NOT_FOUND);
    }


}
