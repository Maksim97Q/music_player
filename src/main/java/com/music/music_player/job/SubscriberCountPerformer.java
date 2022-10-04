package com.music.music_player.job;

import com.music.music_player.repository.PerformerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SubscriberCountPerformer {
    private final PerformerRepository performerRepository;

    @Scheduled(cron = "0 0 01 * * *")
    public void SubscriberCount() {
        log.info(performerRepository.findSumSubscribePerformer());
    }
}
