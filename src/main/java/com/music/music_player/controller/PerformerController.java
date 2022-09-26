package com.music.music_player.controller;

import com.music.music_player.entities.Performer;
import com.music.music_player.service.impl.PerformerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + PERFORMER_URL)
@RequiredArgsConstructor
public class PerformerController {
    private final PerformerServiceImpl performerService;

    @GetMapping(ID)
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Performer> findById(@PathVariable Long id) {
        return Optional.ofNullable(performerService.findPerformerById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Performer> create(@RequestBody Performer performer) {
        return Optional.ofNullable(performerService.createPerformer(performer))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(ID)
//     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Performer> update(@RequestBody Performer performer, @PathVariable Long id) {
        return Optional.ofNullable(performerService.updatePerformerById(performer, id))
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
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Performer>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<Performer> performerList = performerService.findAll(pageable).getContent();
        return new ResponseEntity<>(performerList, HttpStatus.OK);
    }

    @PostMapping("/subscribe/{name}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Performer> subscribeToPerformer(@PathVariable String name) {
        return Optional.ofNullable(performerService.subscribeToPerformer(name))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/unsubscribe/{name}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Performer> unsubscribeFromPerformer(@PathVariable String name) {
        return Optional.ofNullable(performerService.unsubscribeFromPerformer(name))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.noContent().build());
    }
}
