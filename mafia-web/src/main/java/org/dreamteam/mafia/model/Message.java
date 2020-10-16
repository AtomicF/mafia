package org.dreamteam.mafia.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Сообщение - набор данных, описывающих  отдельное сообщение в чате
 */
@Getter
@Setter
public class Message {

    private String from;
    private String message;

    @Override
    public String toString() {
        return "Message [from=" + from + ", message=" + message + "]";
    }
}