package org.dreamteam.mafia.exceptions;

/**
 * Исключение, выбрасываемое при попытке изменить состояние уже законченной игры
 */
public class GameIsOverException extends Exception {

    GameIsOverException() {
        super();
    }

    GameIsOverException(String msg) {
        super(msg);
    }
}
