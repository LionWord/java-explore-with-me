package com.lionword.mainservice.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lionword.mainservice.entity.event.EventFullDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
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
    @JsonIgnore
    @OneToMany(mappedBy = "initiator")
    private List<EventFullDto> initiatedEvents = List.of();

}
