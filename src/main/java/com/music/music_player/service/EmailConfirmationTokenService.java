package com.music.music_player.service;

import com.music.music_player.entities.ConfirmationToken;
import com.music.music_player.entities.User;

public interface EmailConfirmationTokenService {
    ConfirmationToken create(User user);

    ConfirmationToken findByToken(String token);

    void delete(ConfirmationToken token);
}
