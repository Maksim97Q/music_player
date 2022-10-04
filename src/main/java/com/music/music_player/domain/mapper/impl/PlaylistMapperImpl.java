package com.music.music_player.domain.mapper.impl;

import com.music.music_player.domain.dto.request.PlaylistDtoRequest;
import com.music.music_player.domain.dto.response.PlaylistDtoResponse;
import com.music.music_player.domain.entities.Playlist;
import com.music.music_player.domain.entities.Song;
import com.music.music_player.domain.entities.User;
import com.music.music_player.domain.mapper.PlaylistMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaylistMapperImpl implements PlaylistMapper {
    private final ModelMapper modelMapper;

    @Override
    public PlaylistDtoResponse toPlaylistResponse(Playlist playlist) {
        modelMapper.typeMap(Playlist.class, PlaylistDtoResponse.class)
                .addMappings(s -> s.using(MappingContext::getSource)).setPostConverter(toDtoConverter());
        return Objects.isNull(playlist) ? null : modelMapper.map(playlist, PlaylistDtoResponse.class);
    }

    @Override
    public Playlist fromPlaylistRequest(PlaylistDtoRequest playlistDtoRequest) {
        return Objects.isNull(playlistDtoRequest) ? null : modelMapper
                .map(playlistDtoRequest, Playlist.class);
    }

    @Override
    public PlaylistDtoResponse toPlaylistResponseSave(Playlist playlist) {
        return Objects.isNull(playlist) ? null : modelMapper.map(playlist, PlaylistDtoResponse.class);
    }

    private Converter<Playlist, PlaylistDtoResponse> toDtoConverter() {
        return context -> {
            Playlist source = context.getSource();
            context.getDestination()
                    .setSongId(source.getSongs().stream()
                            .map(Song::getId)
                            .collect(Collectors.toList()));
            context.getDestination()
                    .setUserId(source.getUser().stream()
                            .map(User::getId)
                            .collect(Collectors.toList()));
            return context.getDestination();
        };
    }
}
