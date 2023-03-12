package com.lionword.mainservice.entity.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.compilation.Compilation;
import com.lionword.mainservice.entity.location.Location;
import com.lionword.mainservice.entity.user.UserDto;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class EventFullDto {
    @Id
    @ReadOnlyProperty
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "annotation")
    @NonNull
    @Length(min = 20, max = 2000)
    private String annotation;
    @JoinColumn(name = "category")
    @NonNull
    @ManyToOne
    private CategoryDto category;
    @Column(name = "confirmed_requests")
    private int confirmedRequests = 0;
    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn = LocalDateTime.now();
    @Column(name = "description")
    @Length(min = 20, max = 7000)
    private String description;
    @Column(name = "event_date")
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "initiator")
    private UserDto initiator;
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location location;
    @Column(name = "paid")
    @NonNull
    private Boolean paid;
    @Column(name = "participant_limit")
    private int participantLimit;
    @Column(name = "published_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    @Column(name = "request_moderation")
    private boolean requestModeration;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private EventState state = EventState.PENDING;
    @Column(name = "title")
    @NonNull
    @Length(min = 3, max = 120)
    private String title;
    @Column(name = "views")
    private long views = 0;
    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private Set<Compilation> compilationsIncludingThisEvent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventFullDto that = (EventFullDto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
