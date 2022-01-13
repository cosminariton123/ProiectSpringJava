package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelId;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_stars")
    private Integer number_of_stars;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "hotelList")
    private List<LongHaul> longHaulList;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber_of_stars() {
        return number_of_stars;
    }

    public void setNumber_of_stars(Integer number_of_stars) {
        this.number_of_stars = number_of_stars;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<LongHaul> getLongHaulList() {
        return longHaulList;
    }

    public void setLongHaulList(List<LongHaul> longHaulList) {
        this.longHaulList = longHaulList;
    }
}
