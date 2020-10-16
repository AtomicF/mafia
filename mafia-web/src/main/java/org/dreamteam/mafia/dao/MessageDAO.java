package org.dreamteam.mafia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "messages")

public class MessageDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", unique = true, nullable = false)
    private int messageId;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDAO user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", nullable = false)
    private GameDAO game;

    @Column(name = "addressee", nullable = false)
    private int addressee;

    @Override
    public String toString() {
        return "Message{" + text +
                '}';
    }
}
