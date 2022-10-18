package com.music.music_player.domain.mapper;

import com.music.music_player.domain.dto.request.UserDtoRequest;
import com.music.music_player.domain.dto.response.UserDtoResponse;
import com.music.music_player.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapstruct {
    public UserDtoResponse toUserResponse(User user);

    public User fromUserRequest(UserDtoRequest userDtoRequest);

    UserDtoResponse toUserResponseSave(User user);
}
