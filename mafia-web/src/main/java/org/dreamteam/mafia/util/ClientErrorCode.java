package org.dreamteam.mafia.util;

import lombok.Getter;

/**
 * Перечисление ошибок, возвращаемых с кодами HTTP 400
 */
@Getter
public enum ClientErrorCode {
    PASSWORD_MISMATCH(1),
    USER_ALREADY_EXISTS(2),
    USER_NOT_EXISTS(3),
    INCORRECT_PASSWORD(4);

    private final int value;

    ClientErrorCode(int value) {
        this.value = value;
    }
}
