package com.music.music_player.service;

import com.music.music_player.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUser(User user);

    User findUserById(Long id);

    User updateUserById(User user, Long id);

    void deleteUserById(Long id);

    Page<User> findAll(Pageable pageable);

    User findByEmail(String email);
}
