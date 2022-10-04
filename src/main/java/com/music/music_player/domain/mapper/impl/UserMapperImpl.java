package com.music.music_player.domain.mapper.impl;

import com.music.music_player.domain.dto.request.UserDtoRequest;
import com.music.music_player.domain.dto.response.UserDtoResponse;
import com.music.music_player.domain.entities.Performer;
import com.music.music_player.domain.entities.Playlist;
import com.music.music_player.domain.entities.User;
import com.music.music_player.domain.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final ModelMapper modelMapper;

    @Override
    public UserDtoResponse toUserResponse(User user) {
        modelMapper.typeMap(User.class, UserDtoResponse.class)
                .addMappings(s -> s.using(MappingContext::getSource)
                        .map(a -> a.getRole().getName(), UserDtoResponse::setRoleName))
                .setPostConverter(toDtoConverter());
        return Objects.isNull(user) ? null : modelMapper.map(user, UserDtoResponse.class);
    }

    @Override
    public User fromUserRequest(UserDtoRequest userDtoRequest) {
        return Objects.isNull(userDtoRequest) ? null : modelMapper.map(userDtoRequest, User.class);
    }

    @Override
    public UserDtoResponse toUserResponseSave(User user) {
        return Objects.isNull(user) ? null : modelMapper.map(user, UserDtoResponse.class);
    }

    private Converter<User, UserDtoResponse> toDtoConverter() {
        return context -> {
            User source = context.getSource();
            context.getDestination()
                    .setPlaylistsId(source.getPlaylists().stream()
                            .map(Playlist::getId)
                            .collect(Collectors.toList()));
            context.getDestination()
                    .setPerformersId(source.getPerformers().stream()
                            .map(Performer::getId)
                            .collect(Collectors.toList()));
            return context.getDestination();
        };
    }
}
