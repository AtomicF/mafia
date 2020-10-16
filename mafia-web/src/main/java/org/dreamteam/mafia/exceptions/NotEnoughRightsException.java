package org.dreamteam.mafia.exceptions;

/**
 * Исключение, выбрасываемое при попытке совершить действие, разрешенное только администратору комнаты, не являясь таковым
 */
public class NotEnoughRightsException extends Exception {

    NotEnoughRightsException() {
        super();
    }

    NotEnoughRightsException(String msg) {
        super(msg);
    }
}
