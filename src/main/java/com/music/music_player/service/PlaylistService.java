package com.music.music_player.service;

import com.music.music_player.entities.Performer;
import com.music.music_player.entities.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist);

    Playlist findPlaylistById(Long id);

    Playlist updatePlaylistById(Playlist playlist, Long id);

    void deletePlaylistById(Long id);

    Page<Playlist> findAll(Pageable pageable);
}
