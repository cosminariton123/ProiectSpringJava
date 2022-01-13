package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToMany
    @JoinTable
    private List<Hotel> hotelList;

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public Integer getLongHaulId() {
        return longHaulId;
    }

    public void setLongHaulId(Integer longHaulId) {
        this.longHaulId = longHaulId;
    }

    public String getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(String startingAddress) {
        this.startingAddress = startingAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
