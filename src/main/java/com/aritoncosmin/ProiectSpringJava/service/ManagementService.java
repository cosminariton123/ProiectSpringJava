package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.repository.DriverRepository;
import com.aritoncosmin.ProiectSpringJava.repository.LongHaulRepository;
import com.aritoncosmin.ProiectSpringJava.repository.PlaylistRepository;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagementService {
    TruckRepository truckRepository;
    DriverRepository driverRepository;
    LongHaulRepository longHaulRepository;
    PlaylistRepository playlistRepository;

    ManagementService(TruckRepository truckRepository, DriverRepository driverRepository, LongHaulRepository longHaulRepository, PlaylistRepository playlistRepository){
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
        this.longHaulRepository = longHaulRepository;
        this.playlistRepository = playlistRepository;
    }

    public Truck findTruckById(Integer truckId){
        Truck truck = truckRepository.findTruckById(truckId);
        if (truck == null)
            throw new NotFoundException("Truck with id " + truckId + " not found");
        return truck;
    }

    public Truck saveTruck(Truck truck){
        return truckRepository.save(truck);}

    public Truck modifyTruck(Truck truck){
        Truck foundTruck = findTruckById(truck.getId());
        foundTruck.setKm(truck.getKm());
        foundTruck.setBrand(truck.getBrand());
        return saveTruck(truck);
    }

    @Transactional
    public Truck deleteTruckById(Integer truckId){
        Truck deletedTruck = findTruckById(truckId);

        Driver driver = driverRepository.findDriverByTruckId(truckId);

        if (driver != null){
            driver.setTruck(null);
            saveDriver(driver);
        }

        List<LongHaul> longHaulList = longHaulRepository.findLongHaulsByTruckId(truckId);

        for (LongHaul longHaul:
             longHaulList) {
            longHaul.setTruck(null);
            longHaulRepository.save(longHaul);
        }

        Integer deletedCount =  truckRepository.deleteTruckById(truckId);

        if (deletedCount > 0)
            return deletedTruck;

        throw new InternalServerError("Deleted count <= 0, but truck with id " + deletedTruck.getId() + " exists");
    }

    public Driver findDriverById(Integer driverId){
        Driver foundDriver = driverRepository.findDriversById(driverId);

        if (foundDriver == null)
            throw new NotFoundException("Driver with id " + driverId + " not found");
        return foundDriver;
    }

    public Driver saveDriver(Driver driver){
        Driver driverAlreadyAssignedToTruck = driverRepository.findDriverByTruckId(driver.getTruck().getId());

        if (driverAlreadyAssignedToTruck != null)
            throw new BadRequest("Given truck is already driven by driver with id " + driverAlreadyAssignedToTruck.getId());

        return driverRepository.save(driver);
    }

    public Driver modifyDriver(Driver driver){
        Driver foundDriver = findDriverById(driver.getId());
        foundDriver.setTruck(driver.getTruck());
        foundDriver.setPlaylists(driver.getPlaylists());
        foundDriver.setFirstName(driver.getFirstName());
        foundDriver.setAge(driver.getAge());
        foundDriver.setLastName(driver.getLastName());

        return foundDriver;
    }
}
