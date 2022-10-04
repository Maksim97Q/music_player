package com.music.music_player.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column
    private Boolean enabled;
    @ManyToMany
    private List<Playlist> playlists;
    @ManyToMany
    private List<Performer> performers;
    @ManyToOne
    private Role role;

    public void addPerformer(Performer performer) {
        this.performers.add(performer);
        performer.getUsers().add(this);
    }

    public void removePerformer(Performer performer) {
        this.performers.remove(performer);
        performer.getUsers().remove(this);
    }
}
