package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Driver findDriverByTruckId(Integer id);

    Driver findDriversById(Integer id);
}
