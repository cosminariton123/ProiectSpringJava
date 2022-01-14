package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService){
        this.managementService = managementService;
    }

    @GetMapping("truck/{id}")
    public ResponseEntity<Truck> getTruck(@PathVariable Integer id){
        return ResponseEntity.ok().body(managementService.findTruckById(id));
    }

    @PostMapping("/truck")
    public ResponseEntity<Truck> saveNewTruck(@RequestBody Truck truck){
        Truck savedTruck = managementService.saveTruck(truck);
        return ResponseEntity.created(URI.create("/management/truck/" + savedTruck.getTruckId())).body(savedTruck);
    }

    @DeleteMapping("truck/{id}")
    public ResponseEntity<Truck> deleteTruck(@PathVariable Integer id){
        Truck deletedTruck = managementService.deleteTruckById(id);
        return ResponseEntity.ok().body(deletedTruck);
    }
}
