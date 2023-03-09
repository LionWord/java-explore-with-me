package com.lionword.mainservice.entity.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lionword.mainservice.entity.event.EventFullDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Getter
@Setter
@RequiredArgsConstructor
public class Location {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(name = "event_id")
 //   @JsonIgnore
    private long eventId;
    @Column(name = "latitude")
    private float lat;
    @Column(name = "longitude")
    private float lon;

}
