package org.dreamteam.mafia.exceptions;

/**
 * Исключение, выбрасываемое при попытке обратиться к несуществующей комнате
 */
public class NoSuchRoomException extends Exception {

    NoSuchRoomException() {
        super();
    }

    NoSuchRoomException(String msg) {
        super(msg);
    }
}
