package com.music.music_player.controller;

import com.music.music_player.domain.dto.request.UserDtoRequest;
import com.music.music_player.domain.dto.response.UserDtoResponse;
import com.music.music_player.domain.mapper.UserMapper;
import com.music.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.music.music_player.domain.util.UrlConstants.*;

@RestController
@RequestMapping(VERSION + USER_URL)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(ID)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDtoResponse> findById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .map(userService::findUserById)
                .map(userMapper::toUserResponse)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(ID)
//        @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDtoResponse> update(@RequestBody UserDtoRequest userDtoRequest,
                                                  @PathVariable Long id) {
        return Optional.ofNullable(userDtoRequest)
                .map(userMapper::fromUserRequest)
                .map(user -> userService.updateUserById(user, id))
                .map(userMapper::toUserResponse)
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
    public ResponseEntity<List<UserDtoResponse>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        List<UserDtoResponse> userList = userService.findAll(pageable)
                .map(userMapper::toUserResponse).getContent();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
