package com.lionword.entity.category;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoryDto {
    @Id
    @Column(name = "id")
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    @NonNull
    private String name;
}
