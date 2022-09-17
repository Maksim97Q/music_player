package com.music.music_player.service.impl;

import com.music.music_player.entities.Song;
import com.music.music_player.repository.SongRepository;
import com.music.music_player.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    @Override
    public Song createSong(Song song) {
        song.setCreated(LocalDateTime.now());
        return songRepository.save(song);
    }

    @Override
    public Song findSongById(Long id) {
        return songRepository.findById(id).orElse(new Song());
    }

    @Override
    public Song updateSongById(Song song, Long id) {
        Song songById = findSongById(id);
        songById.setTitle(song.getTitle());
        return songRepository.save(songById);
    }

    @Override
    public void deleteSongById(Long id) {
        if (findSongById(id) != null) {
            songRepository.deleteById(id);
        }
    }
}
