package com.music.music_player.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerformerDtoRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
