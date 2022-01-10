package com.aritoncosmin.ProiectSpringJava.model;


import javax.persistence.*;

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

}
