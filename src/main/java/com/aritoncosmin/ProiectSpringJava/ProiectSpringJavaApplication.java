package com.aritoncosmin.ProiectSpringJava;

import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import com.aritoncosmin.ProiectSpringJava.model.Song;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.repository.*;
import com.aritoncosmin.ProiectSpringJava.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProiectSpringJavaApplication implements CommandLineRunner {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private TruckRepository truckRepository;

	@Autowired
	private ManagementService managementService;

	@Autowired
	private LongHaulRepository longHaulRepository;

	@Autowired
	private DriverRepository driverRepository;

	/*
		Driver -- Truck  => 1 to 1
		Driver -- Playlist => M to M
		Truck -- LongHaul => 1 to M
	 */

	public static void main(String[] args) {
		SpringApplication.run(ProiectSpringJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		Song s1 = new Song();
		Song s2 = new Song();

		s1.setName("Burning Love");
		s1.setDuration(69);

		s2.setName("Never gonna give you up");
		s2.setDuration(420);

		songRepository.save(s1);
		songRepository.save(s2);

		Playlist p1 = new Playlist();
		p1.setName("ChefulTiristilor");
		p1.getSongList().add(s1);
		p1.getSongList().add(s2);

		playlistRepository.save(p1);



		Truck t1 = new Truck();
		t1.setBrand("Man");
		t1.setKm(150);

		truckRepository.save(t1);


		Driver d1 = new Driver();
		d1.setTruck(t1);
		driverRepository.save(d1);


	}
}
