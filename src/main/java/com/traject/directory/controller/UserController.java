/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.controller;

import com.traject.directory.model.User;
import com.traject.directory.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author miguelangel
 */
@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("{id}")
    public User findUser(@PathVariable Long id) {
        return userService
                .findUserById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}
