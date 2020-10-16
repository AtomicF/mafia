package org.dreamteam.mafia.exceptions;

import org.dreamteam.mafia.util.ClientErrorCode;

/**
 * Класс родительского исключения для ошибок вызванных неверным запросом со стороны клиента
 * Содержит внутренний код ошибки.
 */
public class ClientErrorException extends Exception {
    private final ClientErrorCode code;

    public ClientErrorException(String message, ClientErrorCode code) {
        super(message);
        this.code = code;
    }

    public ClientErrorException(ClientErrorCode code) {
        super();
        this.code = code;
    }

    public ClientErrorCode getCode() {
        return this.code;
    }
}
