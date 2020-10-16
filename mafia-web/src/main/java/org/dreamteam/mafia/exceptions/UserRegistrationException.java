package org.dreamteam.mafia.exceptions;

import lombok.Getter;
import org.dreamteam.mafia.util.ClientErrorCode;

/**
 * Исключение, выбрасываемое при ошибках в процессе регистрации пользователя
 */
@Getter
public class UserRegistrationException extends ClientErrorException {

    public UserRegistrationException(ClientErrorCode code) {
        super(code);
    }

    public UserRegistrationException(ClientErrorCode code, String msg) {
        super(msg, code);
    }
}
