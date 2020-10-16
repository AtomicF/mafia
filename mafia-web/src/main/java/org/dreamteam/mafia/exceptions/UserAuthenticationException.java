package org.dreamteam.mafia.exceptions;

import lombok.Getter;
import org.dreamteam.mafia.util.ClientErrorCode;

/**
 * Исключение, выбрасываемое при ошибках в процессе аутентификации пользователя в системе
 */
@Getter
public class UserAuthenticationException extends ClientErrorException {

    public UserAuthenticationException(ClientErrorCode code) {
        super(code);
    }

    public UserAuthenticationException(ClientErrorCode code, String msg) {
        super(msg, code);
    }
}
