package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    Playlist findPlaylistById(Integer id);
}
