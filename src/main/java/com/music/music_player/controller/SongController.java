package com.music.music_player.controller;

import com.music.music_player.domain.dto.request.SongDtoRequest;
import com.music.music_player.domain.dto.response.SongDtoResponse;
import com.music.music_player.domain.mapper.SongMapper;
import com.music.music_player.service.SongService;
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
@RequestMapping(VERSION + SONG_URL)
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;
    private final SongMapper songMapper;

    @GetMapping(ID)
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SongDtoResponse> findById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .map(songService::findSongById)
                .map(songMapper::toSongResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SongDtoResponse> create(@RequestBody @Valid SongDtoRequest songDtoRequest) {
        return Optional.ofNullable(songDtoRequest)
                .map(songMapper::fromSongRequest)
                .map(songService::createSong)
                .map(songMapper::toSongResponseSave)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SongDtoResponse> update(@RequestBody @Valid SongDtoRequest songDtoRequest,
                                                  @PathVariable Long id) {
        return Optional.ofNullable(songDtoRequest)
                .map(songMapper::fromSongRequest)
                .map(song -> songService.updateSongById(song, id))
                .map(songMapper::toSongResponse)
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
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SongDtoResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<SongDtoResponse> songList = songService.findAll(pageable)
                .map(songMapper::toSongResponse).getContent();
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }
}
