package com.music.music_player.service.impl;

import com.music.music_player.config.CustomUserDetailsService;
import com.music.music_player.domain.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CurrentUserServiceImpl {
    private final CustomUserDetailsService customUserDetailsService;

    public User getCurrentUser() {
        log.info("Получение текущего пользователя из Security Context");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User result = customUserDetailsService.getUserByEmail(auth.getName());
        log.info("Текущеий пользователь из Security Context получен");
        return result;
    }
}
