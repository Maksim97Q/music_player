package com.music.music_player.service.impl;

import com.music.music_player.entities.Song;
import com.music.music_player.repository.SongRepository;
import com.music.music_player.service.SongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    @Override
    public Song createSong(Song song) {
        log.info("создание песни: {}", song);
        song.setCreated(LocalDateTime.now());
        return songRepository.save(song);
    }

    @Override
    public Song findSongById(Long id) {
        log.info("получение песни по id: {}", id);
        return songRepository.findById(id).orElse(new Song());
    }

    @Override
    public Song updateSongById(Song song, Long id) {
        log.info("изменение песни по id: {}", id);
        Song songById = findSongById(id);
        songById.setTitle(song.getTitle());
        return songRepository.save(songById);
    }

    @Override
    public void deleteSongById(Long id) {
        log.info("удаление песни по id: {}", id);
        if (findSongById(id) != null) {
            songRepository.deleteById(id);
        }
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        log.info("получение всех песен");
        return songRepository.findAll(pageable);
    }
}
