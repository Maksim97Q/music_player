package com.music.music_player.domain.mapper.impl;

import com.music.music_player.domain.dto.request.SongDtoRequest;
import com.music.music_player.domain.dto.response.SongDtoResponse;
import com.music.music_player.domain.entities.Song;
import com.music.music_player.domain.mapper.SongMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SongMapperImpl implements SongMapper {
    private final ModelMapper modelMapper;

    @Override
    public SongDtoResponse toSongResponse(Song song) {
        modelMapper.typeMap(Song.class, SongDtoResponse.class)
                .addMappings(s -> s.using(MappingContext::getSource)
                        .map(a -> a.getPerformer().getId(), SongDtoResponse::setPerformerId))
                .addMappings(s -> s.using(MappingContext::getSource)
                        .map(a -> a.getAlbum().getId(), SongDtoResponse::setAlbumId));
        return Objects.isNull(song) ? null : modelMapper.map(song, SongDtoResponse.class);
    }

    @Override
    public Song fromSongRequest(SongDtoRequest songDtoRequest) {
        return Objects.isNull(songDtoRequest) ? null : modelMapper.map(songDtoRequest, Song.class);
    }

    @Override
    public SongDtoResponse toSongResponseSave(Song song) {
        return Objects.isNull(song) ? null : modelMapper.map(song, SongDtoResponse.class);
    }
}
