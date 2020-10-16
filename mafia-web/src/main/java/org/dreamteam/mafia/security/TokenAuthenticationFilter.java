package org.dreamteam.mafia.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Класс фильтра для цепочки аутентификации.
 * Извлекает из HTTP заголовка или параметра GET запроса фразу вида
 * "Bearer <token>", затем достает из нее значение токена и пытается аутентифицировать пользователя по нему
 */
public final class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String BEARER = "Bearer";
    private final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    public TokenAuthenticationFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
    }

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response) {
        final String param = request.getHeader(HttpHeaders.AUTHORIZATION);

        logger.debug("Authentication attempt. Header: " + param);

        final String token = Optional.ofNullable(param)
                .filter(value -> value.startsWith(BEARER))
                .map(value -> value.substring(BEARER.length()))
                .map(String::trim)
                .orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));

        final Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
        return getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
