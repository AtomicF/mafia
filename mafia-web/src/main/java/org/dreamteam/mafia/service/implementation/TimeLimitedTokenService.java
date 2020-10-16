package org.dreamteam.mafia.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.dreamteam.mafia.model.SignedJsonWebToken;
import org.dreamteam.mafia.model.User;
import org.dreamteam.mafia.service.api.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

/**
 * Класс реализующий выдачу JWT c ограниченным сроком действия: 24 часа
 */
@Service
public class TimeLimitedTokenService implements TokenService {

    private final static long TIME_LIMIT_IN_HOURS = 24;

    private final SecretKey secretKey;

    @Autowired
    public TimeLimitedTokenService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public SignedJsonWebToken getTokenFor(User user) {
        final Instant now = Instant.now();
        final Instant expiredBy = now.plus(Duration.ofHours(TIME_LIMIT_IN_HOURS));
        final Claims claims = Jwts
                .claims()
                .setExpiration(Date.from(expiredBy))
                .setIssuedAt(Date.from(now))
                .setSubject(user.getLogin());
        return new SignedJsonWebToken(
                Jwts.builder()
                        .setClaims(claims)
                        .signWith(secretKey)
                        .compact()
        );
    }

    @Override
    public Optional<String> extractUsernameFrom(SignedJsonWebToken token) {
        final JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
        try {
            Claims claims = parser.parseClaimsJws(token.getValue()).getBody();
            return Optional.ofNullable(claims.getSubject());
        } catch (JwtException e) {
            return Optional.empty();
        }
    }
}
