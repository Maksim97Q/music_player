package com.music.music_player.service.impl;

import com.music.music_player.entities.Role;
import com.music.music_player.entities.User;
import com.music.music_player.repository.UserRepository;
import com.music.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        User byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail == null) {
            user.setEnabled(false);
            user.setRole(new Role(1L, "ROLE_USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            log.info("создание пользователя: {}", user);
            return user;
        }
        return null;
    }

    @Override
    public User findUserById(Long id) {
        log.info("получение пользователя по id: {}", id);
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public User updateUserById(User user, Long id) {
        log.info("изменение пользователя по id: {}", id);
        User userById = findUserById(id);
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        return userRepository.save(userById);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("удаление пользователя по id: {}", id);
        if (findUserById(id) != null) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.info("получение всех пользователей");
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByEmail(String email) {
        log.info("получение пользователя по email: {}", email);
        return userRepository.findByEmail(email);
    }

    public boolean findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if (user != null && user.getEnabled()) {
            log.info("прошла проверка пароля по encode");
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
