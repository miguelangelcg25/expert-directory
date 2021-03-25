/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.service;

import com.traject.directory.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author miguelangel
 */
public interface UserService {

    User createUser(User user);

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    User updateUser(User user);
    
}
