package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.repository.DriverRepository;
import com.aritoncosmin.ProiectSpringJava.repository.LongHaulRepository;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagementService {
    TruckRepository truckRepository;
    DriverRepository driverRepository;
    LongHaulRepository longHaulRepository;

    ManagementService(TruckRepository truckRepository, DriverRepository driverRepository, LongHaulRepository longHaulRepository){
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
        this.longHaulRepository = longHaulRepository;
    }

    public Truck findTruckById(Integer truckId){
        Truck truck = truckRepository.findTruckByTruckId(truckId);
        if (truck == null)
            throw new NotFoundException("Truck with id " + truckId + " not found");
        return truck;
    }

    public Truck saveTruck(Truck truck){
        return truckRepository.save(truck);}

    public Driver saveDriver(Driver driver){ return driverRepository.save(driver); }

    @Transactional
    public Truck deleteTruckById(Integer truckId){
        Truck deletedTruck = findTruckById(truckId);

        Driver driver = driverRepository.findDriverByTruckTruckId(truckId);

        if (driver != null){
            driver.setTruck(null);
            saveDriver(driver);
        }

        List<LongHaul> longHaulList = longHaulRepository.findLongHaulsByTruckTruckId(truckId);

        for (LongHaul longHaul:
             longHaulList) {
            longHaul.setTruck(null);
            longHaulRepository.save(longHaul);
        }

        Integer deletedCount =  truckRepository.deleteTruckByTruckId(truckId);

        if (deletedCount > 0)
            return deletedTruck;

        throw new InternalServerError("Deleted count <= 0, but truck with id " + deletedTruck.getTruckId() + " exists");
    }
}
