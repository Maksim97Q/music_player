package com.music.music_player.service.impl;

import com.music.music_player.entities.User;
import com.music.music_player.repository.UserRepository;
import com.music.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public User updateUserById(User user, Long id) {
        User userById = findUserById(id);
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        return userRepository.save(userById);
    }

    @Override
    public void deleteUserById(Long id) {
        if (findUserById(id) != null) {
            userRepository.deleteById(id);
        }
    }
}
