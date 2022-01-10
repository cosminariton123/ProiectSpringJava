package com.aritoncosmin.ProiectSpringJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProiectSpringJavaApplication {


	/*
		Driver -- Truck  => 1 to 1
		Driver -- Playlist => M to M
		Truck -- LongHaul => 1 to M
	 */

	public static void main(String[] args) {
		SpringApplication.run(ProiectSpringJavaApplication.class, args);
	}

}
