package com.music.music_player.service.impl;

import com.music.music_player.entities.Performer;
import com.music.music_player.entities.User;
import com.music.music_player.repository.PerformerRepository;
import com.music.music_player.repository.UserRepository;
import com.music.music_player.service.PerformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerformerServiceImpl implements PerformerService {
    private final PerformerRepository performerRepository;
    private final CurrentUserServiceImpl currentUserService;

    @Override
    public Performer createPerformer(Performer performer) {
        return performerRepository.save(performer);
    }

    @Override
    public Performer findPerformerById(Long id) {
        return performerRepository.findById(id).orElse(new Performer());
    }

    @Override
    @Transactional
    public Performer updatePerformerById(Performer performer, Long id) {
        Performer performerById = findPerformerById(id);
        performerById.setName(performer.getName());
        return performerRepository.save(performerById);
    }

    @Override
    @Transactional
    public void deletePerformerById(Long id) {
        if (findPerformerById(id) != null) {
            performerRepository.deleteById(id);
        }
    }

    @Override
    public Page<Performer> findAll(Pageable pageable) {
        return performerRepository.findAll(pageable);
    }

    @Transactional
    public Performer subscribeToPerformer(String name) {
        User user = currentUserService.getCurrentUser();
        Performer performer = performerRepository.findByName(name);
        Optional<Performer> first = user.getPerformers().stream()
                .filter(s -> s.getId().equals(performer.getId()))
                .findFirst();
        if (performer != null && first.isEmpty()) {
            user.addPerformer(performer);
            performer.setSubscribersCount(performer.getSubscribersCount() + 1);
            performerRepository.save(performer);
            return performer;
        }
        return null;
    }

    @Transactional
    public Performer unsubscribeFromPerformer(String name) {
        User user = currentUserService.getCurrentUser();
        Performer performer = performerRepository.findByName(name);
        Optional<Performer> first = user.getPerformers().stream()
                .filter(s -> s.getId().equals(performer.getId()))
                .findFirst();
        if (performer != null && first.isPresent()) {
            performer.setSubscribersCount(performer.getSubscribersCount() - 1);
            user.removePerformer(performer);
            performerRepository.save(performer);
            return performer;
        }
        return null;
    }
}
