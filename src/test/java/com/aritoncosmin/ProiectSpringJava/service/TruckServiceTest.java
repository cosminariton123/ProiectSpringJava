package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TruckServiceTest {

    @InjectMocks
    private TruckService truckService;

    @Mock
    private TruckRepository truckRepository;

    @Test
    void save(){
        Truck newTruck = new Truck();
        newTruck.setBrand("Man");
        newTruck.setKm(150);

        Truck savedTruck = new Truck();
        savedTruck.setTruckId(1);
        savedTruck.setBrand(newTruck.getBrand());
        savedTruck.setKm(newTruck.getKm());

        when(truckRepository.save(newTruck)).thenReturn(savedTruck);

        Truck result = truckService.save(newTruck);

        assertEquals(savedTruck.getBrand(), result.getBrand());
    }
}
