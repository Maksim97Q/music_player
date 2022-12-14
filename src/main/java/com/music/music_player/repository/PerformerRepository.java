package com.music.music_player.repository;

import com.music.music_player.domain.entities.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {
    @Query("select (sum(subscribersCount)) from Performer")
    Long findSumSubscribePerformer();

    @Query("select p from Performer p where p.name = ?1")
    Performer findByName(String name);
}
