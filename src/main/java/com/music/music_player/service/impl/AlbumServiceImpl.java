package com.music.music_player.service.impl;

import com.music.music_player.entities.Album;
import com.music.music_player.repository.AlbumRepository;
import com.music.music_player.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public Album createAlbum(Album album) {
        album.setCreated(LocalDateTime.now());
        albumRepository.save(album);
        return album;
    }

    @Override
    public Album findAlbumById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Override
    public Album updateAlbumById(Album album, Long id) {
        Album albumById = findAlbumById(id);
        albumById.setTitle(album.getTitle());
        return albumRepository.save(albumById);
    }

    @Override
    public void deleteAlbumById(Long id) {
        if (findAlbumById(id) != null) {
            albumRepository.deleteById(id);
        }
    }
}
