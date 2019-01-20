package com.quangtmn.socialnetwork.controllers;

import com.quangtmn.socialnetwork.entities.User;
import com.quangtmn.socialnetwork.messages.Message;
import com.quangtmn.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path = "/all-users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping(path = "/new-user")
    public ResponseEntity insertUser(@Valid @RequestBody User user) {
        userService.insertUser(user);
        return ResponseEntity.ok().body(Message.INSERT_USER_SUCCESS);
    }

    @GetMapping(path = "/user/{username}")
    public Resource<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());

        resource.add(linkTo.withRel("all-students"));

        return resource;
    }

    @PutMapping(path = "/update/user/{id}")
    public ResponseEntity updateUserById(@PathVariable int id, @RequestBody User user) {
        userService.updateById(id, user);
        return ResponseEntity.ok().body(Message.USER_UPDATED);
    }

    @DeleteMapping(path = "/delete/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok().body(Message.DELETE_USER_SUCCESS);
    }

    @GetMapping(path = "/user/id/{id}")
    public Resource<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());

        resource.add(linkTo.withRel("all-students"));

        return resource;
    }
}
