package com.music.music_player.domain.mapper.impl;

import com.music.music_player.domain.dto.request.PerformerDtoRequest;
import com.music.music_player.domain.dto.response.PerformerDtoResponse;
import com.music.music_player.domain.entities.Performer;
import com.music.music_player.domain.entities.User;
import com.music.music_player.domain.mapper.PerformerMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PerformerMapperImpl implements PerformerMapper {
    private final ModelMapper modelMapper;

    @Override
    public PerformerDtoResponse toPerformerResponse(Performer performer) {
        modelMapper.typeMap(Performer.class, PerformerDtoResponse.class)
                .addMappings(s -> s.using(MappingContext::getSource)).setPostConverter(toDtoConverter());
        return Objects.isNull(performer) ? null : modelMapper.map(performer, PerformerDtoResponse.class);
    }

    @Override
    public PerformerDtoResponse toPerformerResponseSave(Performer performer) {
        return Objects.isNull(performer) ? null : modelMapper.map(performer, PerformerDtoResponse.class);
    }

    @Override
    public Performer fromSongRequest(PerformerDtoRequest performerDtoRequest) {
        return Objects.isNull(performerDtoRequest) ? null : modelMapper.map(performerDtoRequest, Performer.class);
    }

    private Converter<Performer, PerformerDtoResponse> toDtoConverter() {
        return context -> {
            Performer source = context.getSource();
            context.getDestination().setUserId(source.getUsers().stream()
                    .map(User::getId)
                    .collect(Collectors.toList()));
            return context.getDestination();
        };
    }
}
