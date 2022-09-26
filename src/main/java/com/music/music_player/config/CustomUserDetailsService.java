package com.music.music_player.config;

import com.music.music_player.entities.User;
import com.music.music_player.repository.UserRepository;
import com.music.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }

    public User getUserByEmail(String email) {
        log.info("Поиск пользователя с email: {}", email);
        User result = userRepository.findByEmail(email);
        log.info("Пользователь с email: {} найден", email);
        return result;
    }
}
