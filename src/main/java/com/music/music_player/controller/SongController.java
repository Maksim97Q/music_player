package com.music.music_player.controller;

import com.music.music_player.entities.Song;
import com.music.music_player.service.impl.SongServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + SONG_URL)
@RequiredArgsConstructor
public class SongController {
    private final SongServiceImpl songService;

    @GetMapping(ID)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Song> findById(@PathVariable Long id) {
        return Optional.ofNullable(songService.findSongById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Song> create(@RequestBody Song song) {
        return Optional.ofNullable(songService.createSong(song))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Song> update(@RequestBody Song song, @PathVariable Long id) {
        return Optional.ofNullable(songService.updateSongById(song, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        songService.deleteSongById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(LIST_URL)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Song>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<Song> songList = songService.findAll(pageable).getContent();
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }
}
