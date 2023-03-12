package com.lionword.mainservice.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lionword.mainservice.entity.event.EventFullDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Email
    private String email;
    @Column(name = "name")
    @NonNull
    @Length(max = 50)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "initiator")
    private List<EventFullDto> initiatedEvents = List.of();

}
