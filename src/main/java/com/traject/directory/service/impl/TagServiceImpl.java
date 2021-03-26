/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.service.impl;

import com.traject.directory.model.Tag;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.traject.directory.repository.TagRepository;
import com.traject.directory.service.TagService;

/**
 *
 * @author miguelangel
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Set<Tag> findByNames(Set<String> names) {
        return tagRepository.findByNames(names);
    }

    @Override
    public Set<Tag> saveAll(Set<Tag> newTags) {
        return Set.of(tagRepository.saveAll(newTags).toArray(new Tag[]{}));
    }

}
