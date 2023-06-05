package com.Music.Music.service;

import com.Music.Music.model.Song;
import com.Music.Music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;
    public List<Song> getAllSong(){
        return songRepository.findAll();
    }
}
