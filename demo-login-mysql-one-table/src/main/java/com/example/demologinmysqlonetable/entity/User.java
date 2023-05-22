package com.example.demologinmysqlonetable.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "username", unique = true)
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
}
