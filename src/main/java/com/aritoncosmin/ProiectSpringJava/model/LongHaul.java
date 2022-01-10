package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "long_hauls")
public class LongHaul {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer longHaulId;

    @Column(name = "starting_address")
    private String startingAddress;

    @Column(name = "destination_address")
    private String destinationAddress;

    @ManyToOne
    @JoinColumn(name = "truck")
    private Truck truck;
}
