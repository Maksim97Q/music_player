package com.music.music_player.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDtoResponse {
    private Long id;
    private String title;
    private List<Long> songId;
    private List<Long> userId;
}
