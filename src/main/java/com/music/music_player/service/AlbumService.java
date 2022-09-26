package com.music.music_player.service;

import com.music.music_player.entities.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {
    Album createAlbum(Album album);

    Album findAlbumById(Long id);

    Album updateAlbumById(Album album, Long id);

    void deleteAlbumById(Long id);

    Page<Album> findAll(Pageable pageable);
}
