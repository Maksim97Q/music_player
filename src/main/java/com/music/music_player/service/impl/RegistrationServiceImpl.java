package com.music.music_player.service.impl;

import com.music.music_player.entities.ConfirmationToken;
import com.music.music_player.entities.User;
import com.music.music_player.service.EmailConfirmationTokenService;
import com.music.music_player.service.EmailSenderService;
import com.music.music_player.service.RegistrationService;
import com.music.music_player.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.music.music_player.domain.util.UrlConstants.*;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final EmailConfirmationTokenService emailConfirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final UserService userService;
    private final static String EMAIL_SUBJECT = "Подтверждение аккаунта";
    private final static String LINK = "http://" +
            HOST_URL + ":" + PORT +
            VERSION +
            REGISTRATION_URL +
            CONFIRMATION_URL +
            "?token=";

    @Override
    @Transactional
    public boolean register(User user) {
        User serviceUser = userService.createUser(user);
        if (serviceUser != null) {
            ConfirmationToken token = emailConfirmationTokenService.create(serviceUser);
            String linkWithToken = LINK + token.getToken();
            String head = String.format("Приветствуем вас, %s\n", user.getEmail());
            String div1 = "Добро пожаловать в Player Music!\n";
            String div2 = "Для активации аккаунта пройдите по ссылке ниже\n";
            String resultMessage = head + div1 + div2 + linkWithToken;
            emailSenderService.sendHtml(user.getEmail(), EMAIL_SUBJECT, resultMessage);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void confirm(String token) {
        ConfirmationToken confirmationToken = emailConfirmationTokenService.findByToken(token);
        LocalDateTime localDateTime = confirmationToken.getExpired();
        if (localDateTime.isBefore(now())) {
            userService.deleteUserById(confirmationToken.getUser().getId());
            emailConfirmationTokenService.delete(confirmationToken);
        }
        confirmationToken.getUser().setEnabled(true);
        emailConfirmationTokenService.delete(confirmationToken);
    }
}
