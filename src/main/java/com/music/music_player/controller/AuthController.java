package com.music.music_player.controller;

import com.music.music_player.config.jwt.JwtProvider;
import com.music.music_player.domain.dto.request.UserDtoRequest;
import com.music.music_player.service.UserService;
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
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping(LOGIN_URL)
    public ResponseEntity<String> login(@RequestBody UserDtoRequest userDtoRequest) {
        String token = jwtProvider.generateToken(userDtoRequest.getEmail());
        boolean byEmailAndPassword = userService
                .findByEmailAndPassword(userDtoRequest.getEmail(), userDtoRequest.getPassword());
        return byEmailAndPassword
                ? new ResponseEntity<>(token, (HttpStatus.OK))
                : new ResponseEntity<>("Incorrect login or password", HttpStatus.NOT_FOUND);
    }
}
