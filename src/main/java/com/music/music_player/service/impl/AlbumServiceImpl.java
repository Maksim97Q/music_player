package com.music.music_player.service.impl;

import com.music.music_player.entities.Album;
import com.music.music_player.repository.AlbumRepository;
import com.music.music_player.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public Album createAlbum(Album album) {
        album.setCreated(LocalDateTime.now());
        albumRepository.save(album);
        log.info("создание альбома: {}", album);
        return album;
    }

    @Override
    public Album findAlbumById(Long id) {
        log.info("получение альбома по id: {}", id);
        return albumRepository.findById(id).orElse(null);
    }

    @Override
    public Album updateAlbumById(Album album, Long id) {
        log.info("изменение альбома по id: {}", id);
        Album albumById = findAlbumById(id);
        albumById.setTitle(album.getTitle());
        return albumRepository.save(albumById);
    }

    @Override
    public void deleteAlbumById(Long id) {
        log.info("удаление альбома по id: {}", id);
        if (findAlbumById(id) != null) {
            albumRepository.deleteById(id);
        }
    }

    @Override
    public Page<Album> findAll(Pageable pageable) {
        log.info("получение всех альбомов");
        return albumRepository.findAll(pageable);
    }
}
