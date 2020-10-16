package org.dreamteam.mafia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "statistics")

public class StatisticsDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id", unique = true, nullable = false)
    private int statisticsId;

    @Column(name = "games_total_as_mafia", nullable = false)
    private int gamesTotalAsMafia;

    @Column(name = "games_total_as_sheriff", nullable = false)
    private int gamesTotalAsSheriff;

    @Column(name = "games_total_as_citizen", nullable = false)
    private int gamesTotalAsCitizen;

    @Column(name = "games_won_as_mafia", nullable = false)
    private int gamesWonAsMafia;

    @Column(name = "games_won_as_sheriff", nullable = false)
    private int gamesWonAsSheriff;

    @Column(name = "games_won_as_citizen", nullable = false)
    private int gamesWonAsCitizen;

    @OneToOne(mappedBy = "statistics")
    private UserDAO user;

    @Override
    public String toString() {
        return "Statistics{#" + statisticsId +
                '}';
    }
}
