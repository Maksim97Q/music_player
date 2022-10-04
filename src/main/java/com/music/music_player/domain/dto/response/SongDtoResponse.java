package com.music.music_player.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDtoResponse {
    private Long id;
    private String title;
    private Long performerId;
    private Long albumId;
}
