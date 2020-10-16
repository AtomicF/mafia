package org.dreamteam.mafia.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")

public class RoleDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Column(name = "role", nullable = false)
    private String role;

    @Override
    public String toString() {
        return "Role{" + role +
                '}';
    }
}
