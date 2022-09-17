package com.music.music_player.service;

import com.music.music_player.entities.User;

public interface UserService {
    User createUser(User user);

    User findUserById(Long id);

    User updateUserById(User user, Long id);

    void deleteUserById(Long id);
}
