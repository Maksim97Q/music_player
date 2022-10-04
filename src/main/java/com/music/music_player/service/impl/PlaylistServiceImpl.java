package com.music.music_player.service.impl;

import com.music.music_player.domain.entities.Playlist;
import com.music.music_player.repository.PlaylistRepository;
import com.music.music_player.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        log.info("создание плейлиста: {}", playlist);
        playlist.setCreated(LocalDateTime.now());
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist findPlaylistById(Long id) {
        log.info("получение плейлиста по id: {}", id);
        return playlistRepository.findById(id).orElse(null);
    }

    @Override
    public Playlist updatePlaylistById(Playlist playlist, Long id) {
        log.info("изменение плейлиста по id: {}", id);
        Playlist playlistById = findPlaylistById(id);
        playlistById.setTitle(playlist.getTitle());
        return playlistRepository.save(playlistById);
    }

    @Override
    public void deletePlaylistById(Long id) {
        log.info("удаление плейлиста по id: {}", id);
        if (findPlaylistById(id) != null) {
            playlistRepository.deleteById(id);
        }
    }

    @Override
    public Page<Playlist> findAll(Pageable pageable) {
        log.info("получение всех плейлистов");
        return playlistRepository.findAll(pageable);
    }
}
