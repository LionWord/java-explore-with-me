package com.lionword.entity.location;

import com.lionword.entity.event.EventFullDto;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "event_id")
    private long eventId;
    @Column(name = "latitude")
    private float lat;
    @Column(name = "longitude")
    private float lon;
    @OneToOne(mappedBy = "location")
    private EventFullDto event;
}
