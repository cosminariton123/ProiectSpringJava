package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Hotel;
import com.aritoncosmin.ProiectSpringJava.repository.HotelRepository;
import org.springframework.stereotype.Service;

@Service
public class HousingService {

    HotelRepository hotelRepository;

    public HousingService(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    public Hotel findHotelById(Integer id){
        Hotel hotel = hotelRepository.findHotelById(id);

        if (hotel == null)
            throw new NotFoundException("Hotel with id " + id + " not found");
        return hotel;
    }

}
