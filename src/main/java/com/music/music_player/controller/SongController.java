package com.music.music_player.controller;

import com.music.music_player.entities.Song;
import com.music.music_player.service.impl.SongServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.music.music_player.domain.util.UrlConstants.*;


import java.util.Optional;

@RestController
@RequestMapping(VERSION + SONG_URL)
@RequiredArgsConstructor
public class SongController {
    private final SongServiceImpl songService;

    @GetMapping(ID)
    public ResponseEntity<Song> findById(@PathVariable Long id) {
        return Optional.ofNullable(songService.findSongById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Song> create(@RequestBody Song song) {
        return Optional.ofNullable(songService.createSong(song))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
    public ResponseEntity<Song> update(@RequestBody Song song, @PathVariable Long id) {
        return Optional.ofNullable(songService.updateSongById(song, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        songService.deleteSongById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
