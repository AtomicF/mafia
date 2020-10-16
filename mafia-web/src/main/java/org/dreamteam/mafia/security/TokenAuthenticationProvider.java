package org.dreamteam.mafia.security;

import org.dreamteam.mafia.dao.UserDAO;
import org.dreamteam.mafia.model.SecurityUserDetails;
import org.dreamteam.mafia.model.SignedJsonWebToken;
import org.dreamteam.mafia.repository.api.CrudUserRepository;
import org.dreamteam.mafia.service.api.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Класс обеспечивающий аутентификацию пользователя по токену.
 * Обращается к сервису токенов для получения из токена имени пользователя,
 * а затем пытается найти такового в базе.
 */
@Component
public final class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(TokenAuthenticationProvider.class);
    private final TokenService tokenService;
    private final CrudUserRepository repository;

    @Autowired
    public TokenAuthenticationProvider(TokenService tokenService, CrudUserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        // Намеренно оставлено пустым.
    }

    @Override
    protected UserDetails retrieveUser(
            String userName,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        final Object token = usernamePasswordAuthenticationToken.getCredentials();
        logger.debug("Retrieving user via token. " + userName + " : " + token);
        Optional<String> login = tokenService.extractUsernameFrom(new SignedJsonWebToken(token.toString()));
        if (login.isPresent()) {
            List<UserDAO> userDAOS = repository.findByLogin(login.get());
            if (userDAOS.size() > 0) {
                return new SecurityUserDetails(userDAOS.get(0));
            }
        }
        throw new UsernameNotFoundException("Cannot find user with authentication token=" + token);
    }
}
