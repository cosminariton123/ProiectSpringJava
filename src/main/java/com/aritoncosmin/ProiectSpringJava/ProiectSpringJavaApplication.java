package com.aritoncosmin.ProiectSpringJava;

import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import com.aritoncosmin.ProiectSpringJava.model.Song;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.repository.LongHaulRepository;
import com.aritoncosmin.ProiectSpringJava.repository.PlaylistRepository;
import com.aritoncosmin.ProiectSpringJava.repository.SongRepository;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.SwitchPoint;
import java.util.List;

@SpringBootApplication
public class ProiectSpringJavaApplication implements CommandLineRunner {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private TruckRepository truckRepository;

	@Autowired
	private LongHaulRepository longHaulRepository;

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

		s1.setName("coco");
		s1.setDuration(69);

		s2.setName("Mirciu");
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

		LongHaul l1 = new LongHaul();
		LongHaul l2 = new LongHaul();
		l1.setTruck(t1);
		l1.setDestinationAddress("Pula");
		l1.setStartingAddress("Romania");
		l2.setTruck(t1);
		l2.setStartingAddress("Romania");
		l2.setDestinationAddress("Atena");

		longHaulRepository.save(l1);
		longHaulRepository.save(l2);


	}
}
