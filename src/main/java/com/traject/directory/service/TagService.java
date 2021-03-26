/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.service;

import com.traject.directory.model.Tag;
import java.util.Set;

/**
 *
 * @author miguelangel
 */
public interface TagService {

    Set<Tag> findByNames(Set<String> names);

    Set<Tag> saveAll(Set<Tag> newTags);

}
