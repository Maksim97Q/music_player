package com.music.music_player.controller;

import com.music.music_player.domain.dto.request.PlaylistDtoRequest;
import com.music.music_player.domain.dto.response.PlaylistDtoResponse;
import com.music.music_player.domain.mapper.PlaylistMapper;
import com.music.music_player.service.PlaylistService;
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
@RequestMapping(VERSION + PLAYLIST_URL)
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final PlaylistMapper playlistMapper;

    @GetMapping(ID)
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PlaylistDtoResponse> findById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .map(playlistService::findPlaylistById)
                .map(playlistMapper::toPlaylistResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//      @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PlaylistDtoResponse> create(@RequestBody @Valid PlaylistDtoRequest playlistDtoRequest) {
        return Optional.ofNullable(playlistDtoRequest)
                .map(playlistMapper::fromPlaylistRequest)
                .map(playlistService::createPlaylist)
                .map(playlistMapper::toPlaylistResponseSave)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PlaylistDtoResponse> update(@RequestBody PlaylistDtoRequest playlistDtoRequest,
                                                      @PathVariable Long id) {
        return Optional.ofNullable(playlistDtoRequest)
                .map(playlistMapper::fromPlaylistRequest)
                .map(playlist -> playlistService.updatePlaylistById(playlist, id))
                .map(playlistMapper::toPlaylistResponse)
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
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PlaylistDtoResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<PlaylistDtoResponse> playlistList = playlistService.findAll(pageable)
                .map(playlistMapper::toPlaylistResponse).getContent();
        return new ResponseEntity<>(playlistList, HttpStatus.OK);
    }

}
