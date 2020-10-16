package org.dreamteam.mafia.controller;

import org.dreamteam.mafia.dto.LoginDTO;
import org.dreamteam.mafia.dto.RegistrationDTO;
import org.dreamteam.mafia.exceptions.UserAuthenticationException;
import org.dreamteam.mafia.exceptions.UserRegistrationException;
import org.dreamteam.mafia.model.SignedJsonWebToken;
import org.dreamteam.mafia.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для регистрации и входа в систему пользователей
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обрабатывает запросы на регистрацию
     *
     * @param dto - запрос на регистрацию
     * @return - JWT вновь зарегистрированного пользователя
     * @throws UserRegistrationException   - при ошибках регистрации
     * @throws UserAuthenticationException - при ошибках авторизации вновь зарегестрированного пользователя
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
            @RequestBody RegistrationDTO dto) throws UserRegistrationException, UserAuthenticationException {
        logger.debug("Incoming registration request. DTO: " + dto);
        userService.registerNewUser(dto);
        SignedJsonWebToken jws = userService.loginUser(dto.getLoginData());
        return jws.getValue();
    }

    /**
     * Обрабатывает запросы на вход в систему
     *
     * @param dto - запрос на вход в систему
     * @return - JWT пользователя
     * @throws UserAuthenticationException- при ошибках авторизации
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginDTO dto) throws UserAuthenticationException {
        logger.debug("Incoming login request. DTO: " + dto);
        SignedJsonWebToken jws = userService.loginUser(dto);
        return jws.getValue();
    }
}
