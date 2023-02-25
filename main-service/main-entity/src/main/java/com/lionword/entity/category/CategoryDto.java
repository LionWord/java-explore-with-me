package com.lionword.entity.category;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CategoryDto {
    @Id
    private long id;
    private String name;
}
