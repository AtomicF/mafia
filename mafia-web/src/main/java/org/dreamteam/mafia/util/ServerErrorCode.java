package org.dreamteam.mafia.util;

import lombok.Getter;

/**
 * Перечисление ошибок, возвращаемых с кодами HTTP 500
 */
@Getter
public enum ServerErrorCode {
    SERVER_CODE_FAILURE(1),
    DB_FAILURE(2);

    private final int value;

    ServerErrorCode(int value) {
        this.value = value;
    }
}
