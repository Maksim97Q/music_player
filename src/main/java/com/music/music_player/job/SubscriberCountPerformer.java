package com.music.music_player.job;

import com.music.music_player.repository.PerformerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriberCountPerformer {
    private final PerformerRepository performerRepository;

    @Scheduled(cron = "0 0 01 * * *")
    public void SubscriberCount() {
        System.out.println(performerRepository.findSumSubscribePerformer());
    }
}
