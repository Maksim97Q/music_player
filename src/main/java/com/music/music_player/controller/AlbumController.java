package com.music.music_player.controller;

import com.music.music_player.entities.Album;
import com.music.music_player.service.impl.AlbumServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + ALBUM_URL)
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumServiceImpl albumService;

    @GetMapping(ID)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Album> findById(@PathVariable Long id) {
        return Optional.ofNullable(albumService.findAlbumById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Album> create(@RequestBody @Valid Album album) {
        return Optional.ofNullable(albumService.createAlbum(album))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Album> update(@RequestBody @Valid Album album, @PathVariable Long id) {
        return Optional.ofNullable(albumService.updateAlbumById(album, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(LIST_URL)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Album>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<Album> albumList = albumService.findAll(pageable).getContent();
        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }
}
