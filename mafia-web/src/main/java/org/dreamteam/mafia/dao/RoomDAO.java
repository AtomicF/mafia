package org.dreamteam.mafia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rooms")

public class RoomDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", unique = true, nullable = false)
    private int roomId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password_hash")
    private long passwordHash;

    @Column(name = "users_amount", nullable = false)
    private int usersAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private GameDAO game;

    //@OneToMany(mappedBy = "room")
    //private List<User> userList;

    @Override
    public String toString() {
        return "Room{#" + roomId +
                '}';
    }
}
