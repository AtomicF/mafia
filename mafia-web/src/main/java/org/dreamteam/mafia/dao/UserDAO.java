package org.dreamteam.mafia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Объект, связанный с таблицей пользователей в БД
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")

public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private long userId;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "login", unique = true, nullable = false, length = 100)
    private String login;
/*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics_id")
    private StatisticsDAO statistics;

    @OneToMany(mappedBy = "user")
    private List<MessageDAO> messageList;

    public UserDAO(String login, String passwordHash) {
        this.passwordHash = passwordHash;
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                '}';
    }
}
