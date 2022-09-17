package com.music.music_player.controller;

import com.music.music_player.entities.Album;
import com.music.music_player.service.impl.AlbumServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.music.music_player.domain.util.UrlConstants.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(VERSION + ALBUM_URL)
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumServiceImpl albumService;

    @GetMapping(ID)
    public ResponseEntity<Album> findById(@PathVariable Long id) {
        return Optional.ofNullable(albumService.findAlbumById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Album> create(@RequestBody @Valid Album album) {
        return Optional.ofNullable(albumService.createAlbum(album))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(ID)
    public ResponseEntity<Album> update(@RequestBody @Valid Album album, @PathVariable Long id) {
        return Optional.ofNullable(albumService.updateAlbumById(album, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
