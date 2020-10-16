package org.dreamteam.mafia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * JSON запрос на регистрацию нового пользователя
 */
@Getter
@Setter
@NoArgsConstructor
public class RegistrationDTO implements Serializable {

    private String login;
    private String password;
    private String passwordConfirmation;

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                '}';
    }

    /**
     * Извлекает из данных для регистрации подмножество данных для логина
     *
     * @return - данные для логина
     */
    public LoginDTO getLoginData() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin(this.login);
        loginDTO.setPassword(this.password);
        return loginDTO;
    }
}


