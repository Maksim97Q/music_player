package com.music.music_player.controller;

import com.music.music_player.entities.User;
import com.music.music_player.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + USER_URL)
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping(ID)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return Optional.ofNullable(userService.findUserById(id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        return Optional.ofNullable(userService.updateUserById(user, id))
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(ID)
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(LIST_URL)
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<User> userList = userService.findAll(pageable).getContent();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
