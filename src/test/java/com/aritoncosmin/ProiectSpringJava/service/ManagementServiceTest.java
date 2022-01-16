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
        searchedTruck.setId(1);
        searchedTruck.setBrand("a");
        searchedTruck.setKm(1);

        when(truckRepository.findTruckById(searchedTruck.getId())).thenReturn(searchedTruck);

        Truck result = managementService.findTruckById(searchedTruck.getId());

        assertEquals(searchedTruck.getBrand(), result.getBrand());
    }

    @Test
    @DisplayName("Running findTruckById in a sad flow")
    void findTruckByIdSadFlow(){
        Truck searchedTruck = new Truck();
        searchedTruck.setId(-1);
        searchedTruck.setBrand("a");
        searchedTruck.setKm(1);

        when(truckRepository.findTruckById(searchedTruck.getId())).thenReturn(null);

        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> managementService.findTruckById(searchedTruck.getId()));

        assertEquals("Truck with id " + searchedTruck.getId() + " not found", exception.getMessage());
    }


    @Test
    @DisplayName("Running saveTruck")
    void saveTruck(){
        Truck newTruck = new Truck();
        newTruck.setBrand("a");
        newTruck.setKm(1);

        Truck savedTruck = new Truck();
        savedTruck.setId(1);
        savedTruck.setBrand(newTruck.getBrand());
        savedTruck.setKm(newTruck.getKm());

        when(truckRepository.save(newTruck)).thenReturn(savedTruck);

        Truck result = managementService.saveTruck(newTruck);

        assertNotNull(result);
        assertEquals(savedTruck.getBrand(), result.getBrand());
    }


    @Test
    @DisplayName("Running Delete Truck By id Happy Flow")
    void deleteTruckByIdHappyFlow(){

        Truck toDeleteTruck = new Truck();
        toDeleteTruck.setId(1);
        toDeleteTruck.setBrand("a");
        toDeleteTruck.setKm(1);

        List<LongHaul> longHaulList = new ArrayList<>();
        LongHaul l1 = new LongHaul();
        LongHaul l2 = new LongHaul();
        longHaulList.add(l1);
        longHaulList.add(l2);

        when(truckRepository.deleteTruckById(toDeleteTruck.getId())).thenReturn(1);
        when(truckRepository.findTruckById(toDeleteTruck.getId())).thenReturn(toDeleteTruck);
        when(driverRepository.findDriverByTruckId(toDeleteTruck.getId())).thenReturn(new Driver());
        when(longHaulRepository.findLongHaulsByTruckId(toDeleteTruck.getId())).thenReturn(longHaulList);

        Truck result = managementService.deleteTruckById(toDeleteTruck.getId());

        assertNotNull(result);
        assertEquals(toDeleteTruck.getBrand(), result.getBrand());

    }

    @Test
    @DisplayName("Running Delete Truck By id Sad Flow")
    void deleteTruckByIdSadFlow(){

        Truck toDeleteTruck = new Truck();
        toDeleteTruck.setId(1);
        toDeleteTruck.setBrand("a");
        toDeleteTruck.setKm(1);

        List<LongHaul> longHaulList = new ArrayList<>();
        LongHaul l1 = new LongHaul();
        LongHaul l2 = new LongHaul();
        longHaulList.add(l1);
        longHaulList.add(l2);

        when(truckRepository.deleteTruckById(toDeleteTruck.getId())).thenReturn(0);
        when(truckRepository.findTruckById(toDeleteTruck.getId())).thenReturn(toDeleteTruck);
        when(driverRepository.findDriverByTruckId(toDeleteTruck.getId())).thenReturn(new Driver());
        when(longHaulRepository.findLongHaulsByTruckId(toDeleteTruck.getId())).thenReturn(longHaulList);

        RuntimeException exception = assertThrows(InternalServerError.class,
                () -> managementService.deleteTruckById(toDeleteTruck.getId()));

        assertEquals("Deleted count <= 0, but truck with id " + toDeleteTruck.getId() + " exists", exception.getMessage());
    }

    @Test
    @DisplayName("Running modifyTruck")
    void modifyTruck(){

        Truck modifiedTruck = new Truck();
        modifiedTruck.setId(1);
        modifiedTruck.setBrand("a");
        modifiedTruck.setKm(2);

        Truck dbTruck = new Truck();
        dbTruck.setId(1);
        dbTruck.setBrand("b");
        dbTruck.setKm(1);

        when(truckRepository.findTruckById(modifiedTruck.getId())).thenReturn(dbTruck);
        when(truckRepository.save(modifiedTruck)).thenReturn(modifiedTruck);

        Truck result = managementService.modifyTruck(modifiedTruck);

        assertNotNull(result);
        assertEquals(modifiedTruck.getId(), result.getId());
        assertEquals(modifiedTruck.getBrand(), result.getBrand());
        assertEquals(modifiedTruck.getKm(), result.getKm());
    }


    @Test
    @DisplayName("Running saveDriver")
    void saveDriver(){
        Driver newDriver = new Driver();
        newDriver.setId(1);
        newDriver.setFirstName("a");

        when(driverRepository.save(newDriver)).thenReturn(newDriver);

        Driver result = managementService.saveDriver(newDriver);

        assertNotNull(result);
        assertEquals(newDriver.getFirstName(), result.getFirstName());
    }


    @Test
    @DisplayName("Running findDriverById")
    void findDriverById(){
        Driver searchedDriver = new Driver();
        searchedDriver.setFirstName("a");
        searchedDriver.setId(1);

        when(driverRepository.findDriversById(searchedDriver.getId())).thenReturn(searchedDriver);

        Driver result = managementService.findDriverById(searchedDriver.getId());

        assertNotNull(result);
        assertEquals(searchedDriver.getFirstName(), result.getFirstName());
    }
}
