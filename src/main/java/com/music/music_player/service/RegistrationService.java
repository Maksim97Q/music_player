package com.music.music_player.service;

import com.music.music_player.entities.User;

public interface RegistrationService {
    boolean register(User user);

    void confirm(String token);
}
