package com.music.music_player.service.impl;

import com.music.music_player.entities.Role;
import com.music.music_player.entities.User;
import com.music.music_player.repository.UserRepository;
import com.music.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        User byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail == null) {
            user.setRole(new Role(1L, "ROLE_USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return user;
        }
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

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
