package com.music.music_player.service;

import com.music.music_player.entities.Performer;

public interface PerformerService {
    Performer createPerformer(Performer performer);

    Performer findPerformerById(Long id);

    Performer updatePerformerById(Performer performer, Long id);

    void deletePerformerById(Long id);
}
