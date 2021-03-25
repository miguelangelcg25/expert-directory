/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.repository;

import com.traject.directory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author miguelangel
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
