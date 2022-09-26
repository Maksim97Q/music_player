package com.music.music_player.controller;

import com.music.music_player.config.jwt.JwtProvider;
import com.music.music_player.entities.User;
import com.music.music_player.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION)
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userService;
    private final JwtProvider jwtProvider;

    @PostMapping(REGISTRATION_URL)
    public ResponseEntity<String> registration(@RequestBody User user) {
        return Optional.ofNullable(userService.createUser(user))
                .map(s -> new ResponseEntity<>("registration completed successfully", HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = jwtProvider.generateToken(user.getEmail());
        boolean byEmailAndPassword = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return byEmailAndPassword
                ? new ResponseEntity<>(token, (HttpStatus.OK))
                : new ResponseEntity<>("Incorrect login or password", HttpStatus.NOT_FOUND);
    }
}
