package com.music.music_player.controller;

import com.music.music_player.entities.User;
import com.music.music_player.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.music.music_player.domain.util.UrlConstants.*;


import java.util.Optional;

@RestController
@RequestMapping(VERSION + USER_URL)
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping(ID)
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return Optional.ofNullable(userService.findUserById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return Optional.ofNullable(userService.createUser(user))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        return Optional.ofNullable(userService.updateUserById(user, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
