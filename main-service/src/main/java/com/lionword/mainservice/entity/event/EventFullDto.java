package com.lionword.mainservice.entity.event;

import com.lionword.mainservice.entity.category.CategoryDto;
import com.lionword.mainservice.entity.compilation.Compilation;
import com.lionword.mainservice.entity.location.Location;
import com.lionword.mainservice.entity.user.UserDto;
import com.lionword.mainservice.entity.user.UserShortDto;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
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
    private String annotation;
    @JoinColumn(name = "category")
    @NonNull
    @ManyToOne
    private CategoryDto category;
    @Column(name = "confirmed_requests")
    private int confirmedRequests;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "description")
    private String description;
    @Column(name = "event_date")
    @NonNull
    private LocalDateTime eventDate;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "initiator")
    private UserDto initiator;
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location", referencedColumnName = "event_id")
    private Location location;
    @Column(name = "paid")
    @NonNull
    private Boolean paid;
    @Column(name = "participant_limit")
    private int participantLimit;
    @Column(name = "published_on")
    private LocalDateTime publishedOn;
    @Column(name = "request_moderation")
    private boolean requestModeration;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private EventState state;
    @Column(name = "title")
    @NonNull
    private String title;
    @Column(name = "views")
    private long views;
    @ManyToMany(mappedBy = "events")
    private Set<Compilation> compilationsIncludingThisEvent;

}
