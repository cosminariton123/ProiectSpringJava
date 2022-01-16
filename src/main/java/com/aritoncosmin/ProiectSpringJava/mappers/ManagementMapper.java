package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.DriverCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.DriverModifyDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.TruckCreateDTO;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.service.ManagementService;
import com.aritoncosmin.ProiectSpringJava.service.MusicService;
import org.springframework.stereotype.Component;

@Component
public class ManagementMapper {

    MusicService musicService;
    ManagementService managementService;

    public ManagementMapper(MusicService musicService, ManagementService managementService){
        this.musicService = musicService;
        this.managementService = managementService;
    }

    public Truck TruckCreateDTOToTruck(TruckCreateDTO truckCreateDTO){
        Truck truck = new Truck();
        truck.setBrand(truckCreateDTO.getBrand());
        truck.setKm(truckCreateDTO.getKm());
        return truck;
    }

    public Driver DriverCreateDTOToDriver(DriverCreateDTO driverCreateDTO){
        Driver driver = new Driver();
        driver.setAge(driverCreateDTO.getAge());
        driver.setFirstName(driverCreateDTO.getFirstName());
        driver.setLastName(driverCreateDTO.getLastName());

        Truck truck = managementService.findTruckById(driverCreateDTO.getTruckId());
        driver.setTruck(truck);

        System.out.println(driverCreateDTO);

        for (Integer playlistId:
             driverCreateDTO.getPlaylistIds()) {
            driver.getPlaylists().add(musicService.findPlaylistById(playlistId));
        }
        return driver;
    }

    public Driver DriverModifyDTOToDriver(DriverModifyDTO driverModifyDTO){
        Driver driver = new Driver();
        driver.setId(driverModifyDTO.getId());
        driver.setAge(driverModifyDTO.getAge());
        driver.setLastName(driverModifyDTO.getLastName());
        driver.setFirstName(driverModifyDTO.getFirstName());

        Truck truck = managementService.findTruckById(driverModifyDTO.getId());
        driver.setTruck(truck);

        for (Integer playlistId:
             driverModifyDTO.getPlaylistIds()) {
            driver.getPlaylists().add(musicService.findPlaylistById(playlistId));
        }
        return driver;
    }
}
