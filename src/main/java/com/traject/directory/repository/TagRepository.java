/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.repository;

import com.traject.directory.model.Tag;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author miguelangel
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "from Tag where name in ?1")
    Set<Tag> findByNames(Set<String> names);

}
