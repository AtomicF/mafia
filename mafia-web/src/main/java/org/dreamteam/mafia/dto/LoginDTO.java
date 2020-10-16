package org.dreamteam.mafia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * JSON запрос на вход в систему пользователя
 */

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO implements Serializable {

    private String login;
    private String password;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


