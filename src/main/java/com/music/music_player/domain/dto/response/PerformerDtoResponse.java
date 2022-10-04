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
public class PerformerDtoResponse {
    private Long id;
    private String name;
    private String description;
    private Integer subscribersCount;
    private List<Long> userId;
}
