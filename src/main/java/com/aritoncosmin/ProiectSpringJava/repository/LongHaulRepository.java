package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LongHaulRepository extends JpaRepository<LongHaul, Integer> {

    List<LongHaul> findLongHaulsByTruckTruckId(Integer id);

}
