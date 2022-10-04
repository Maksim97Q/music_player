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
public class UserDtoResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String roleName;
    private List<Long> performersId;
    private List<Long> playlistsId;
}
