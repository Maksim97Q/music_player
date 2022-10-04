package com.music.music_player.domain.mapper;

import com.music.music_player.domain.dto.request.PerformerDtoRequest;
import com.music.music_player.domain.dto.response.PerformerDtoResponse;
import com.music.music_player.domain.entities.Performer;

public interface PerformerMapper {
    PerformerDtoResponse toPerformerResponse(Performer performer);

    PerformerDtoResponse toPerformerResponseSave(Performer performer);

    Performer fromSongRequest(PerformerDtoRequest performerDtoRequest);
}
