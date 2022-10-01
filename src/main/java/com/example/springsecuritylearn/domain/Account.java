package com.example.springsecuritylearn.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
    private Long Id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}
