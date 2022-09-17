package com.music.music_player.service;

import com.music.music_player.entities.Playlist;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist);

    Playlist findPlaylistById(Long id);

    Playlist updatePlaylistById(Playlist playlist, Long id);

    void deletePlaylistById(Long id);
}
