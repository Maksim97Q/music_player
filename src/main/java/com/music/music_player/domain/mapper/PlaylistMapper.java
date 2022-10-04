package com.music.music_player.domain.mapper;

import com.music.music_player.domain.dto.request.PlaylistDtoRequest;
import com.music.music_player.domain.dto.response.PlaylistDtoResponse;
import com.music.music_player.domain.entities.Playlist;

public interface PlaylistMapper {
    PlaylistDtoResponse toPlaylistResponse(Playlist playlist);

    Playlist fromPlaylistRequest(PlaylistDtoRequest playlistDtoRequest);

    PlaylistDtoResponse toPlaylistResponseSave(Playlist playlist);
}
