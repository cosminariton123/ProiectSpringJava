package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.DriverCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.DriverModifyDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.TruckCreateDTO;
import com.aritoncosmin.ProiectSpringJava.mappers.ManagementMapper;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final ManagementService managementService;
    private final ManagementMapper managementMapper;

    public ManagementController(ManagementService managementService, ManagementMapper managementMapper){
        this.managementService = managementService;
        this.managementMapper = managementMapper;
    }

    @GetMapping("/truck/{id}")
    public ResponseEntity<Truck> getTruck(@PathVariable Integer id){
        return ResponseEntity.ok().body(managementService.findTruckById(id));
    }

    @PostMapping("/truck")
    public ResponseEntity<Truck> saveNewTruck(@RequestBody TruckCreateDTO truckCreateDTO){
        Truck truck = managementMapper.TruckCreateDTOToTruck(truckCreateDTO);
        Truck savedTruck = managementService.saveTruck(truck);
        return ResponseEntity.created(URI.create("/management/truck/" + savedTruck.getId())).body(savedTruck);
    }

    @PutMapping("/truck")
    public ResponseEntity<Truck> modifyTruck(@RequestBody Truck truck){
        Truck savedTruck = managementService.modifyTruck(truck);
        return ResponseEntity.ok().body(savedTruck);
    }

    @DeleteMapping("truck/{id}")
    public ResponseEntity<Truck> deleteTruck(@PathVariable Integer id){
        Truck deletedTruck = managementService.deleteTruckById(id);
        return ResponseEntity.ok().body(deletedTruck);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable Integer id){
        return ResponseEntity.ok().body(managementService.findDriverById(id));
    }

    @PostMapping("/driver")
    public ResponseEntity<Driver> saveNewDriver(@RequestBody @Valid DriverCreateDTO driverCreateDTO){
        Driver driver = managementMapper.DriverCreateDTOToDriver(driverCreateDTO);
        Driver savedDriver = managementService.saveDriver(driver);
        return ResponseEntity.created(URI.create("/management/driver/" + savedDriver.getId())).body(savedDriver);
    }

    @PutMapping("/driver")
    public ResponseEntity<Driver> modifyDriver(@RequestBody DriverModifyDTO driverModifyDTO){
        Driver driver = managementMapper.DriverModifyDTOToDriver(driverModifyDTO);
        Driver savedDriver = managementService.modifyDriver(driver);
        return ResponseEntity.ok().body(savedDriver);
    }

}
