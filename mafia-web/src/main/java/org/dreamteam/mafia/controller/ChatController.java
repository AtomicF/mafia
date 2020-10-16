package org.dreamteam.mafia.controller;

import org.dreamteam.mafia.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Контроллер для чата
 */
@Controller
public class ChatController {

    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    /**
     * Обрабатывает входящие сообщения
     *
     * @param message - входящеесообщение
     * @return - рассылаемое сообщение
     */
    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message getMessages(Message message) {
        logger.debug("Incoming via /message : " + message);
        return message;
    }
}
