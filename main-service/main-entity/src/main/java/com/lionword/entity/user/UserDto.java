package com.lionword.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
}
