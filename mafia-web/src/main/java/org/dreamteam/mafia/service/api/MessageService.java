package org.dreamteam.mafia.service.api;

import org.dreamteam.mafia.dto.MessageDTO;
import org.dreamteam.mafia.model.User;

/**
 * Интерфейс сервиса чата
 */
public interface MessageService {

    /**
     * Отправляет сообщение от указанного пользователя. Адресаты определяются автоматически.
     *
     * @param user       - отправитель ообщения
     * @param messageDTO - сообщение, полученное от интерфейса
     */
    void sendMessage(User user, MessageDTO messageDTO);
}
