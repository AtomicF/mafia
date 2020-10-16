package org.dreamteam.mafia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Подписанный JWT. Обертка вокруг строки, чтобы было очевиднее, какие из строк - JWT
 */
@Getter
@AllArgsConstructor
public class SignedJsonWebToken {
    private final String value;
}
