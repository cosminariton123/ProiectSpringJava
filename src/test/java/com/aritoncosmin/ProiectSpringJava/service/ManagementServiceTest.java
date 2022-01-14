package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.repository.DriverRepository;
import com.aritoncosmin.ProiectSpringJava.repository.LongHaulRepository;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManagementServiceTest {

    @InjectMocks
    private ManagementService managementService;

    @Mock
    private TruckRepository truckRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private LongHaulRepository longHaulRepository;


    @Test
    @DisplayName("Running find by id Truck in a happy flow")
    void findByIdHappyFlow(){
        Truck searchedTruck = new Truck();
        searchedTruck.setTruckId(1);
        searchedTruck.setBrand("a");
        searchedTruck.setKm(1);

        when(truckRepository.findTruckByTruckId(searchedTruck.getTruckId())).thenReturn(searchedTruck);

        Truck result = managementService.findTruckById(searchedTruck.getTruckId());

        assertEquals(searchedTruck.getBrand(), result.getBrand());
    }

    @Test
    @DisplayName("Running findTruckById in a sad flow")
    void findTruckByIdSadFlow(){
        Truck searchedTruck = new Truck();
        searchedTruck.setTruckId(-1);
        searchedTruck.setBrand("a");
        searchedTruck.setKm(1);

        when(truckRepository.findTruckByTruckId(searchedTruck.getTruckId())).thenReturn(null);

        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> managementService.findTruckById(searchedTruck.getTruckId()));

        assertEquals("Truck with id " + searchedTruck.getTruckId() + " not found", exception.getMessage());
    }


    @Test
    @DisplayName("Running saveTruck")
    void saveTruck(){
        Truck newTruck = new Truck();
        newTruck.setBrand("Man");
        newTruck.setKm(150);

        Truck savedTruck = new Truck();
        savedTruck.setTruckId(1);
        savedTruck.setBrand(newTruck.getBrand());
        savedTruck.setKm(newTruck.getKm());

        when(truckRepository.save(newTruck)).thenReturn(savedTruck);

        Truck result = managementService.saveTruck(newTruck);

        assertNotNull(result);
        assertEquals(savedTruck.getBrand(), result.getBrand());
    }

    @Test
    @DisplayName("Running saveDriver")
    void saveDriver(){
        Driver newDriver = new Driver();
        newDriver.setDriverId(1);
        newDriver.setDriverFirstName("a");

        when(driverRepository.save(newDriver)).thenReturn(newDriver);

        Driver result = managementService.saveDriver(newDriver);

        assertNotNull(result);
        assertEquals(newDriver.getDriverFirstName(), result.getDriverFirstName());
    }

    @Test
    @DisplayName("Running Delete Truck By id Happy Flow")
    void deleteTruckByIdHappyFlow(){

        Truck toDeleteTruck = new Truck();
        toDeleteTruck.setTruckId(1);
        toDeleteTruck.setBrand("a");
        toDeleteTruck.setKm(1);

        List<LongHaul> longHaulList = new ArrayList<>();
        LongHaul l1 = new LongHaul();
        LongHaul l2 = new LongHaul();
        longHaulList.add(l1);
        longHaulList.add(l2);

        when(truckRepository.deleteTruckByTruckId(toDeleteTruck.getTruckId())).thenReturn(1);
        when(truckRepository.findTruckByTruckId(toDeleteTruck.getTruckId())).thenReturn(toDeleteTruck);
        when(driverRepository.findDriverByTruckTruckId(toDeleteTruck.getTruckId())).thenReturn(new Driver());
        when(longHaulRepository.findLongHaulsByTruckTruckId(toDeleteTruck.getTruckId())).thenReturn(longHaulList);

        Truck result = managementService.deleteTruckById(toDeleteTruck.getTruckId());

        assertNotNull(result);
        assertEquals(toDeleteTruck.getBrand(), result.getBrand());

    }

    @Test
    @DisplayName("Running Delete Truck By id Sad Flow")
    void deleteTruckByIdSadFlow(){

        Truck toDeleteTruck = new Truck();
        toDeleteTruck.setTruckId(1);
        toDeleteTruck.setBrand("a");
        toDeleteTruck.setKm(1);

        List<LongHaul> longHaulList = new ArrayList<>();
        LongHaul l1 = new LongHaul();
        LongHaul l2 = new LongHaul();
        longHaulList.add(l1);
        longHaulList.add(l2);

        when(truckRepository.deleteTruckByTruckId(toDeleteTruck.getTruckId())).thenReturn(0);
        when(truckRepository.findTruckByTruckId(toDeleteTruck.getTruckId())).thenReturn(toDeleteTruck);
        when(driverRepository.findDriverByTruckTruckId(toDeleteTruck.getTruckId())).thenReturn(new Driver());
        when(longHaulRepository.findLongHaulsByTruckTruckId(toDeleteTruck.getTruckId())).thenReturn(longHaulList);

        RuntimeException exception = assertThrows(InternalServerError.class,
                () -> managementService.deleteTruckById(toDeleteTruck.getTruckId()));

        assertEquals("Deleted count <= 0, but truck with id " + toDeleteTruck.getTruckId() + " exists", exception.getMessage());
    }
}
