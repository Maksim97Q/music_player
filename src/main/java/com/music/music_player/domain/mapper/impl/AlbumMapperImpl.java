package com.music.music_player.domain.mapper.impl;

import com.music.music_player.domain.dto.request.AlbumDtoRequest;
import com.music.music_player.domain.dto.response.AlbumDtoResponse;
import com.music.music_player.domain.entities.Album;
import com.music.music_player.domain.mapper.AlbumMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AlbumMapperImpl implements AlbumMapper {
    private final ModelMapper modelMapper;

    @Override
    public AlbumDtoResponse toAlbumResponse(Album album) {
        modelMapper.typeMap(Album.class, AlbumDtoResponse.class)
                .addMappings(s -> s.using(MappingContext::getSource)
                        .map(a -> a.getPerformer().getId(), AlbumDtoResponse::setPerformerId));
        return Objects.isNull(album) ? null : modelMapper.map(album, AlbumDtoResponse.class);
    }

    @Override
    public Album fromAlbumRequest(AlbumDtoRequest albumDtoRequest) {
        return Objects.isNull(albumDtoRequest) ? null : modelMapper.map(albumDtoRequest, Album.class);
    }

    @Override
    public AlbumDtoResponse toAlbumResponseSave(Album album) {
        return Objects.isNull(album) ? null : modelMapper.map(album, AlbumDtoResponse.class);
    }
}
