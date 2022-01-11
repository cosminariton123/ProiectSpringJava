package com.aritoncosmin.ProiectSpringJava.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    @Column(name = "driver_first_name")
    private String driverFirstName;

    @Column(name = "driver_last_name")
    private String driverLastName;

    @Column(name = "driver_age")
    private Integer driverAge;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToMany
    @JoinTable
    private List<Playlist> playlists = new ArrayList<>();

}
