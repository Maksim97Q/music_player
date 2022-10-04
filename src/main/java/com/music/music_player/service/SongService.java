package com.music.music_player.service;

import com.music.music_player.domain.entities.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {
    Song createSong(Song song);

    Song findSongById(Long id);

    Song updateSongById(Song song, Long id);

    void deleteSongById(Long id);

    Page<Song> findAll(Pageable pageable);
}
