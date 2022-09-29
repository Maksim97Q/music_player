package com.music.music_player.service;

public interface EmailSenderService {
    void sendHtml(String to, String subject, String text);
}
