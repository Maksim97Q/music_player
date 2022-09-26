package com.music.music_player.service.impl;

import com.music.music_player.entities.Performer;
import com.music.music_player.entities.User;
import com.music.music_player.repository.PerformerRepository;
import com.music.music_player.repository.UserRepository;
import com.music.music_player.service.PerformerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PerformerServiceImpl implements PerformerService {
    private final PerformerRepository performerRepository;
    private final CurrentUserServiceImpl currentUserService;

    @Override
    public Performer createPerformer(Performer performer) {
        log.info("создание исполнителя: {}", performer);
        return performerRepository.save(performer);
    }

    @Override
    public Performer findPerformerById(Long id) {
        log.info("получение исполнителя по id: {}", id);
        return performerRepository.findById(id).orElse(new Performer());
    }

    @Override
    @Transactional
    public Performer updatePerformerById(Performer performer, Long id) {
        log.info("изменение исполнителя по id: {}", id);
        Performer performerById = findPerformerById(id);
        performerById.setName(performer.getName());
        return performerRepository.save(performerById);
    }

    @Override
    @Transactional
    public void deletePerformerById(Long id) {
        log.info("удаление исполнителя по id: {}", id);
        if (findPerformerById(id) != null) {
            performerRepository.deleteById(id);
        }
    }

    @Override
    public Page<Performer> findAll(Pageable pageable) {
        log.info("получение всех исполнителей");
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
            log.info("подписался на исполнителя по имени: {}", name);
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
            log.info("отписался от исполнителя по имени: {}", name);
            return performer;
        }
        return null;
    }
}
