package com.music.music_player.controller;

import com.music.music_player.entities.Playlist;
import com.music.music_player.service.impl.PlaylistServiceImpl;
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
@RequestMapping(VERSION + PLAYLIST_URL)
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistServiceImpl playlistService;

    @GetMapping(ID)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Playlist> findById(@PathVariable Long id) {
        return Optional.ofNullable(playlistService.findPlaylistById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//      @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Playlist> create(@RequestBody Playlist playlist) {
        return Optional.ofNullable(playlistService.createPlaylist(playlist))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Playlist> create(@RequestBody Playlist playlist, @PathVariable Long id) {
        return Optional.ofNullable(playlistService.updatePlaylistById(playlist, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
//      @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistService.deletePlaylistById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(LIST_URL)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Playlist>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<Playlist> playlistList = playlistService.findAll(pageable).getContent();
        return new ResponseEntity<>(playlistList, HttpStatus.OK);
    }

}
