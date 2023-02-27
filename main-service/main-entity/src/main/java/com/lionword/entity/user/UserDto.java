package com.lionword.entity.user;

import com.lionword.entity.event.EventFullDto;
import lombok.NonNull;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDto {
    @Id
    @Column(name = "id")
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    @NonNull
    private String email;
    @Column(name = "name")
    @NonNull
    private String name;

}
