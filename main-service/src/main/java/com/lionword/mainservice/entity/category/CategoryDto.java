package com.lionword.mainservice.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lionword.mainservice.entity.event.EventFullDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDto {

    @Id
    @Column(name = "id")
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotBlank
    @Length(max = 50)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<EventFullDto> events = List.of();

    public CategoryDto(Long id) {
        this.id = id;
    }

}
