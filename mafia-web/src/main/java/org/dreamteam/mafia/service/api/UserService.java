package org.dreamteam.mafia.service.api;

import org.dreamteam.mafia.dto.LoginDTO;
import org.dreamteam.mafia.dto.RegistrationDTO;
import org.dreamteam.mafia.exceptions.UserAuthenticationException;
import org.dreamteam.mafia.exceptions.UserRegistrationException;
import org.dreamteam.mafia.model.SignedJsonWebToken;
import org.dreamteam.mafia.model.User;

import java.util.Optional;

/**
 * Интерфейс сервиса, записсывающего и предоставляющего информацию о пользователе данной сессии
 */
public interface UserService {

    /**
     * Регистрирует нового пользователя в приложении
     *
     * @param registrationDTO - информация о пользователе, полученная из интерфейса
     * @throws UserRegistrationException - если при регистрации возникли проблемы
     */
    void registerNewUser(RegistrationDTO registrationDTO) throws UserRegistrationException;

    /**
     * Вход существующего пользователя в приложение
     *
     * @param loginDTO - информация о пользователе, полученная из интерфейса
     * @return - JWS (подписанный JWT), соответствующий пользователю
     * @throws UserAuthenticationException - если при входе возникли проблемы
     */
    SignedJsonWebToken loginUser(LoginDTO loginDTO) throws UserAuthenticationException;

    /**
     * Возвращает пользователя, авторизованного в данной сессии.
     * Сама авторизация выполняется до передачи управления в контроллеры за счет интерсепторов Spring Security
     *
     * @return - авторизованного в настоящий момент пользователя или null, если пользователь еще не авторизован,
     * например, при вызове этого метода из контроллера, отвечающего за регистрацию пользователя
     */
    Optional<User> getCurrentUser();
}
