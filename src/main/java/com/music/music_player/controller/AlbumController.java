package com.music.music_player.controller;

import com.music.music_player.domain.dto.request.AlbumDtoRequest;
import com.music.music_player.domain.dto.response.AlbumDtoResponse;
import com.music.music_player.domain.mapper.AlbumMapper;
import com.music.music_player.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + ALBUM_URL)
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    @GetMapping(ID)
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AlbumDtoResponse> findById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .map(albumService::findAlbumById)
                .map(albumMapper::toAlbumResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AlbumDtoResponse> create(@RequestBody @Valid AlbumDtoRequest albumDtoRequest) {
        return Optional.ofNullable(albumDtoRequest)
                .map(albumMapper::fromAlbumRequest)
                .map(albumService::createAlbum)
                .map(albumMapper::toAlbumResponseSave)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AlbumDtoResponse> update(@RequestBody @Valid AlbumDtoRequest albumDtoRequest,
                                                   @PathVariable Long id) {
        return Optional.ofNullable(albumDtoRequest)
                .map(albumMapper::fromAlbumRequest)
                .map(album -> albumService.updateAlbumById(album, id))
                .map(albumMapper::toAlbumResponse)
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
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AlbumDtoResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<AlbumDtoResponse> albumList = albumService.findAll(pageable)
                .map(albumMapper::toAlbumResponse).getContent();
        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }
}
