package com.music.music_player.domain.mapper;

import com.music.music_player.domain.dto.request.SongDtoRequest;
import com.music.music_player.domain.dto.response.SongDtoResponse;
import com.music.music_player.domain.entities.Song;

public interface SongMapper {
    SongDtoResponse toSongResponse(Song song);

    Song fromSongRequest(SongDtoRequest songDtoRequest);

    SongDtoResponse toSongResponseSave(Song song);
}
