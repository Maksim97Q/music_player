package com.music.music_player.repository;

import com.music.music_player.domain.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    @Query("select c from ConfirmationToken c where c.token = ?1")
    ConfirmationToken findByToken(String token);
}
