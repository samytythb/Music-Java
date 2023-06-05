package com.Music.Music.controller;

import com.Music.Music.model.Song;
import com.Music.Music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SongController {
    @Autowired
    private SongService songService;
    @GetMapping("/getAllSong")
    public ResponseEntity<List<Song>> getAllSong(){
        return new ResponseEntity<List<Song>>(songService.getAllSong(), HttpStatus.OK);
    }
}
