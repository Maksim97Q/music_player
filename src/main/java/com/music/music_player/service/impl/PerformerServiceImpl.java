package com.music.music_player.service.impl;

import com.music.music_player.entities.Performer;
import com.music.music_player.repository.PerformerRepository;
import com.music.music_player.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformerServiceImpl implements PerformerService {
    private final PerformerRepository performerRepository;

    @Override
    public Performer createPerformer(Performer performer) {
        return performerRepository.save(performer);
    }

    @Override
    public Performer findPerformerById(Long id) {
        return performerRepository.findById(id).orElse(new Performer());
    }

    @Override
    public Performer updatePerformerById(Performer performer, Long id) {
        Performer performerById = findPerformerById(id);
        performerById.setName(performer.getName());
        return performerRepository.save(performerById);
    }

    @Override
    public void deletePerformerById(Long id) {
        if (findPerformerById(id) != null) {
            performerRepository.deleteById(id);
        }
    }
}
