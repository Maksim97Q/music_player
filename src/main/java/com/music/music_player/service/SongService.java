package com.music.music_player.service;

import com.music.music_player.entities.Song;

public interface SongService {
    Song createSong(Song song);

    Song findSongById(Long id);

    Song updateSongById(Song song, Long id);

    void deleteSongById(Long id);
}
