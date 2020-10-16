package org.dreamteam.mafia.service.api;

import org.dreamteam.mafia.model.SignedJsonWebToken;
import org.dreamteam.mafia.model.User;

import java.util.Optional;

/**
 * Интерфейс сервиса, отвечающего за выдачу, верификацию и обслуживание JWT
 */
public interface TokenService {

    /**
     * Генерирует подписанный JWT для указаного пользователя
     *
     * @param user - пользователь для генерации JWT
     * @return - подписанный JWT
     */
    SignedJsonWebToken getTokenFor(User user);

    /**
     * Верифицирует JWT и извлекает из него имя пользователя, которому он принадлежит
     *
     * @param token - подписанный JWT
     * @return - имя пользователя при успешной верификации или пустой Optional при провальной
     */
    Optional<String> extractUsernameFrom(SignedJsonWebToken token);
}
