package org.dreamteam.mafia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для системы комнат
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    /**
     * Заглушка для теста доступности API
     *
     * @return - сообщение о успешности доступа
     */
    @RequestMapping(value = "/getAvailable", method = RequestMethod.GET)
    public String register() {
        return "GET is successful";
    }
}
