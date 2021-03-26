/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.service.impl;

import com.traject.directory.model.Tag;
import com.traject.directory.model.User;
import com.traject.directory.repository.UserRepository;
import com.traject.directory.service.ShortenUrl;
import com.traject.directory.service.UserService;
import com.traject.directory.utils.HTMLScrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.traject.directory.service.TagService;

/**
 *
 * @author miguelangel
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShortenUrl shortenUrlService;

    @Autowired
    private TagService tagService;

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (user.getUrl() != null) {
            CompletableFuture<String> shortenUrlFeature = CompletableFuture.supplyAsync(() -> {
                try {
                    return shortenUrlService.shortenUrl(user.getUrl());
                } catch (Exception ex) {
                    return null;
                }
            });

            CompletableFuture<Set<String>> retrieveTagsFeature = CompletableFuture.supplyAsync(() -> {
                try {
                    HTMLScrapper scrapper = new HTMLScrapper(user.getUrl());
                    scrapper.connect();
                    return scrapper.select("h1,h2,h3");
                } catch (IOException ex) {
                    LOG.error("An error ocurrend when retrieving tags from " + user.getUrl(), ex);
                    return Collections.emptySet();
                }
            });

            CompletableFuture<Set<String>> combinedFeatured = shortenUrlFeature.thenCombine(retrieveTagsFeature, (shortUrl, tags) -> {
                user.setShortURL(shortUrl);
                return tags;
            });

            try {
                Set<String> names = combinedFeatured.get();

                //save tags not existing tags
                Set<Tag> newTags = new HashSet<>(names.stream().map(n -> new Tag(n)).collect(Collectors.toSet()));
                Set<Tag> existingTags = tagService.findByNames(names);
                newTags.removeAll(existingTags);
                newTags = tagService.saveAll(newTags);
                user.setTags(existingTags);
                user.addTags(newTags);

                return userRepository.save(user);
            } catch (Exception ex) {
                LOG.error("Error procesing url " + user.getUrl(), ex);
                return userRepository.save(user);
            }
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
