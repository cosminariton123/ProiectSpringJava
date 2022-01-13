package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantsId;

    @Column(name = "restaurant_name")
    private String name;

    @Column(name = "rating")
    private Integer rating;

    @OneToOne(mappedBy = "restaurant")
    private Hotel hotel;

    public Integer getRestaurantsId() {
        return restaurantsId;
    }

    public void setRestaurantsId(Integer restaurantsId) {
        this.restaurantsId = restaurantsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
