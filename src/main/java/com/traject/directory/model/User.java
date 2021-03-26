/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author miguelangel
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

    @Column(name = "short_url")
    private String shortURL;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_tag", joinColumns = {
        @JoinColumn(name = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id")})
    private Set<Tag> tags;

    @ElementCollection
    @Column(name = "friend_id")
    @JoinTable(name = "user_friend")
    @JsonIgnore
    private Set<Long> friends = new HashSet<>();

    public void addTags(Set<Tag> t) {
        tags.addAll(t);
    }

    @JsonProperty("friends")
    public int friendsCount() {
        if (friends == null) {
            return 0;
        }
        return friends.size();
    }

    public void addFriends(Long... id) {
        friends.addAll(Set.of(id));
    }

}
