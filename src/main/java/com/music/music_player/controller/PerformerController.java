package com.music.music_player.controller;

import com.music.music_player.domain.dto.request.PerformerDtoRequest;
import com.music.music_player.domain.dto.response.PerformerDtoResponse;
import com.music.music_player.domain.mapper.PerformerMapper;
import com.music.music_player.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + PERFORMER_URL)
@RequiredArgsConstructor
public class PerformerController {
    private final PerformerService performerService;
    private final PerformerMapper performerMapper;

    @GetMapping(ID)
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerformerDtoResponse> findById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .map(performerService::findPerformerById)
                .map(performerMapper::toPerformerResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerformerDtoResponse> create(@RequestBody @Valid PerformerDtoRequest performerDtoRequest) {
        return Optional.ofNullable(performerDtoRequest)
                .map(performerMapper::fromSongRequest)
                .map(performerService::createPerformer)
                .map(performerMapper::toPerformerResponseSave)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerformerDtoResponse> update(@RequestBody @Valid PerformerDtoRequest performerDtoRequest,
                                                       @PathVariable Long id) {
        return Optional.ofNullable(performerDtoRequest)
                .map(performerMapper::fromSongRequest)
                .map(performer -> performerService.updatePerformerById(performer, id))
                .map(performerMapper::toPerformerResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
//      @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        performerService.deletePerformerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(LIST_URL)
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<PerformerDtoResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<PerformerDtoResponse> performerList = performerService.findAll(pageable)
                .map(performerMapper::toPerformerResponse).getContent();
        return new ResponseEntity<>(performerList, HttpStatus.OK);
    }

    @PostMapping("/subscribe/{name}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerformerDtoResponse> subscribeToPerformer(@PathVariable String name) {
        return Optional.ofNullable(name)
                .map(performerService::subscribeToPerformer)
                .map(performerMapper::toPerformerResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/unsubscribe/{name}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<PerformerDtoResponse> unsubscribeFromPerformer(@PathVariable String name) {
        return Optional.ofNullable(name)
                .map(performerService::unSubscribeFromPerformer)
                .map(performerMapper::toPerformerResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }
}
