package com.lionword.entity.category;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryDto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
}
