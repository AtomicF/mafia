package org.dreamteam.mafia.repository.implementation;

import org.dreamteam.mafia.dao.UserDAO;
import org.dreamteam.mafia.repository.api.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация простого репозитория пользователей, хранящая их в оперативной памяти
 */
@Repository
@Profile("no_db")
public class UserRepositoryInMemory implements UserRepository {

    private final Map<String, UserDAO> database = new HashMap<>();

    @Override
    public void saveUser(UserDAO user) throws Exception {
        if (database.containsKey(user.getLogin())) {
            throw new Exception();
        }
        database.put(user.getLogin(), user);
    }

    @Override
    public UserDAO getUserByLogin(String login) {
        if (database.containsKey(login)) {
            return database.get(login);
        }
        return null;
    }
}
