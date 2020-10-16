package org.dreamteam.mafia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "games")

public class GameDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", unique = true, nullable = false)
    private int gameId;

    @Column(name = "phase", nullable = false)
    private boolean phase;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "day_number", nullable = false)
    private int numberOfDay;

    @OneToOne(mappedBy = "game")
    private RoomDAO room;

    @OneToMany(mappedBy = "game")
    private List<VotingDAO> votingList;

    @OneToMany(mappedBy = "game")
    private List<CharacterDAO> characterList;

    @OneToMany(mappedBy = "game")
    private List<MessageDAO> messageList;

    @Override
    public String toString() {
        return "Game{#" + gameId +
                '}';
    }
}
