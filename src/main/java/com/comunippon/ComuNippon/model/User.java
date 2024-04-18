package com.comunippon.ComuNippon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private LocalDate creationDate;

    private boolean isLogged;

    @OneToMany
    List<TitleList> titleLists = new ArrayList<>();

    @OneToMany
    List<Review> review = new ArrayList<>();

    @OneToMany
    List<Topic> topics = new ArrayList<>();

    @OneToMany
    List<Comment> comments = new ArrayList<>();



}
