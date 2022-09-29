package com.music.music_player.controller;

import com.music.music_player.config.jwt.JwtProvider;
import com.music.music_player.entities.User;
import com.music.music_player.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.music.music_player.domain.util.UrlConstants.LOGIN_URL;
import static com.music.music_player.domain.util.UrlConstants.VERSION;

@RestController
@RequestMapping(VERSION)
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtProvider jwtProvider;

    @PostMapping(LOGIN_URL)
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = jwtProvider.generateToken(user.getEmail());
        boolean byEmailAndPassword = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return byEmailAndPassword
                ? new ResponseEntity<>(token, (HttpStatus.OK))
                : new ResponseEntity<>("Incorrect login or password", HttpStatus.NOT_FOUND);
    }
}
