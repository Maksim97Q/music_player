package com.music.music_player.controller;

import com.music.music_player.entities.Playlist;
import com.music.music_player.service.impl.PlaylistServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.music.music_player.domain.util.UrlConstants.*;


import java.util.Optional;

@RestController
@RequestMapping(VERSION + PLAYLIST_URL)
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistServiceImpl playlistService;

    @GetMapping(ID)
    public ResponseEntity<Playlist> findById(@PathVariable Long id) {
        return Optional.ofNullable(playlistService.findPlaylistById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Playlist> create(@RequestBody Playlist playlist) {
        return Optional.ofNullable(playlistService.createPlaylist(playlist))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
    public ResponseEntity<Playlist> create(@RequestBody Playlist playlist, @PathVariable Long id) {
        return Optional.ofNullable(playlistService.updatePlaylistById(playlist, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistService.deletePlaylistById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
