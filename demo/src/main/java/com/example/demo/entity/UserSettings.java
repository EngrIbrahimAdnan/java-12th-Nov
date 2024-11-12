package com.example.demo.entity;

import com.example.demo.entity.UserEntity;

import jakarta.persistence.*;

@Entity
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean receiveNewsletter;
    private String preferredLanguage;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // don't forget to generate the Getters and setters
}