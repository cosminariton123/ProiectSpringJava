package com.aritoncosmin.ProiectSpringJava.service;


import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import com.aritoncosmin.ProiectSpringJava.repository.PlaylistRepository;
import com.aritoncosmin.ProiectSpringJava.repository.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class MusicService {
    SongRepository songRepository;
    PlaylistRepository playlistRepository;

    public MusicService(SongRepository songRepository, PlaylistRepository playlistRepository){
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }

    public Playlist findPlaylistById(Integer id){
        Playlist foundPlaylist = playlistRepository.findPlaylistById(id);

        if (foundPlaylist == null)
            throw new NotFoundException("Playlist with id " + id + " not found");

        return foundPlaylist;
    }
}
