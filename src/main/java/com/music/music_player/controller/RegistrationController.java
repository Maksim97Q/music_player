package com.music.music_player.controller;

import com.music.music_player.entities.User;
import com.music.music_player.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.music.music_player.domain.util.UrlConstants.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(VERSION + REGISTRATION_URL)
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody User user) {
        boolean register = registrationService.register(user);
        return register
                ? new ResponseEntity<>("registration was successful, please confirm your email", CREATED)
                : new ResponseEntity<>("this email is already in use", NOT_FOUND);
    }

    @GetMapping(CONFIRMATION_URL)
    public ResponseEntity<?> confirm(@RequestParam String token) {
        registrationService.confirm(token);
        return new ResponseEntity<>(OK);
    }
}
