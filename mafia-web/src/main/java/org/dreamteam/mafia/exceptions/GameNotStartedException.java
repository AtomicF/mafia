package org.dreamteam.mafia.exceptions;

/**
 * Исключение, выбрасываемое при попытке получить игру для комнаты, в которой она еще не начата
 */
public class GameNotStartedException extends Exception {

    GameNotStartedException() {
        super();
    }

    GameNotStartedException(String msg) {
        super(msg);
    }
}
