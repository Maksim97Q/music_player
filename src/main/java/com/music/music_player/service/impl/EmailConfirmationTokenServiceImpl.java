package com.music.music_player.service.impl;

import com.music.music_player.entities.ConfirmationToken;
import com.music.music_player.entities.User;
import com.music.music_player.repository.ConfirmationTokenRepository;
import com.music.music_player.service.EmailConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailConfirmationTokenServiceImpl implements EmailConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private static final Long MINUTES_TO_EXPIRE = 60L;

    @Override
    public ConfirmationToken create(User user) {
        String token = tokenGenerationForVerification();
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(MINUTES_TO_EXPIRE);
        ConfirmationToken confirmationToken = new ConfirmationToken(1L, token, localDateTime, user);
        confirmationToken.setCreated(LocalDateTime.now());
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public void delete(ConfirmationToken token) {
        confirmationTokenRepository.delete(token);
    }

    public String tokenGenerationForVerification() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int number = new Random().nextInt(10);
            token.append(number);
        }
        return token.toString();
    }
}
