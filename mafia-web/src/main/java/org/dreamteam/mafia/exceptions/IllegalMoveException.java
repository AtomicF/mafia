package org.dreamteam.mafia.exceptions;

/**
 * Исключение, выбрасываемое при попытке совершить действие, нарушающее правила игры
 */
public class IllegalMoveException extends Exception {

    IllegalMoveException() {
        super();
    }

    IllegalMoveException(String msg) {
        super(msg);
    }
}
