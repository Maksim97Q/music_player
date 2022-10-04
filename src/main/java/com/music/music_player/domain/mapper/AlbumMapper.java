package com.music.music_player.domain.mapper;

import com.music.music_player.domain.dto.request.AlbumDtoRequest;
import com.music.music_player.domain.dto.response.AlbumDtoResponse;
import com.music.music_player.domain.entities.Album;

public interface AlbumMapper {
    AlbumDtoResponse toAlbumResponse(Album album);

    Album fromAlbumRequest(AlbumDtoRequest albumDtoRequest);

    AlbumDtoResponse toAlbumResponseSave(Album album);
}
