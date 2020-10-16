package org.dreamteam.mafia.repository.api;

import org.dreamteam.mafia.dao.UserDAO;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс простого репозитория для доступа к пользователям
 */
@Repository
public interface UserRepository {

    /**
     * Добавляет пользователя в репозиторий
     *
     * @param user - добавляемый пользователь
     * @throws Exception - при ошибке добавления
     */
    void saveUser(UserDAO user) throws Exception;

    /**
     * Возвращает пользователя по логину
     *
     * @param login - логин
     * @return - пользователь или null
     */
    UserDAO getUserByLogin(String login);
}
