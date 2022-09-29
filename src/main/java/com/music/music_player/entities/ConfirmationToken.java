package com.music.music_player.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String token;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime expired;
    @OneToOne
    private User user;

    public ConfirmationToken(Long id, String token, LocalDateTime expired, User user) {
        this.id = id;
        this.token = token;
        this.expired = expired;
        this.user = user;
    }
}
