package com.quangtmn.socialnetwork.services;

import com.quangtmn.socialnetwork.entities.User;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();

    boolean insertUser(User user);

    User getUserByUsername(String username);

    void updateById(int id, User user);

    void deleteById(int id);

    User getUserById(int id);
}
