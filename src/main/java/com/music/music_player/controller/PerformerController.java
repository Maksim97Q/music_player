package com.music.music_player.controller;

import com.music.music_player.entities.Performer;
import com.music.music_player.service.impl.PerformerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + PERFORMER_URL)
@RequiredArgsConstructor
public class PerformerController {
    private final PerformerServiceImpl performerService;

    @GetMapping(ID)
    public ResponseEntity<Performer> findById(@PathVariable Long id) {
        return Optional.ofNullable(performerService.findPerformerById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Performer> create(@RequestBody Performer performer) {
        return Optional.ofNullable(performerService.createPerformer(performer))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(ID)
    public ResponseEntity<Performer> update(@RequestBody Performer performer, @PathVariable Long id) {
        return Optional.ofNullable(performerService.updatePerformerById(performer, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        performerService.deletePerformerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
