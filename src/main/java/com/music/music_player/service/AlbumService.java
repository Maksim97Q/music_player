package com.music.music_player.service;

import com.music.music_player.entities.Album;

public interface AlbumService {
    Album createAlbum(Album album);

    Album findAlbumById(Long id);

    Album updateAlbumById(Album album, Long id);

    void deleteAlbumById(Long id);
}
