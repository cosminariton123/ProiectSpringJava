package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.springframework.stereotype.Service;

@Service
public class TruckService {
    TruckRepository truckRepository;

    TruckService(TruckRepository truckRepository){
        this.truckRepository = truckRepository;
    }

    Truck save(Truck truck){
        return truckRepository.save(truck);
    }
}
