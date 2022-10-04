package com.music.music_player.domain.mapper;

import com.music.music_player.domain.dto.request.UserDtoRequest;
import com.music.music_player.domain.dto.response.UserDtoResponse;
import com.music.music_player.domain.entities.User;

public interface UserMapper {
    UserDtoResponse toUserResponse(User user);

    User fromUserRequest(UserDtoRequest userDtoRequest);

    UserDtoResponse toUserResponseSave(User user);
}
