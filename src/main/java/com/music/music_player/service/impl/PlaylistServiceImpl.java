package com.music.music_player.service.impl;

import com.music.music_player.entities.Playlist;
import com.music.music_player.repository.PlaylistRepository;
import com.music.music_player.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        playlist.setCreated(LocalDateTime.now());
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist findPlaylistById(Long id) {
        return playlistRepository.findById(id).orElse(new Playlist());
    }

    @Override
    public Playlist updatePlaylistById(Playlist playlist, Long id) {
        Playlist playlistById = findPlaylistById(id);
        playlistById.setTitle(playlist.getTitle());
        return playlistRepository.save(playlistById);
    }

    @Override
    public void deletePlaylistById(Long id) {
        if (findPlaylistById(id) != null) {
            playlistRepository.deleteById(id);
        }
    }
}
