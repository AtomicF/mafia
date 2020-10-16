package org.dreamteam.mafia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Классс ответа с описанием ошибки, возникшей в результате выполнения запроса.
 * Сериализуется в JSON и отправляется клиенту
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {
    private int result;
    private String answerMessage;
}
