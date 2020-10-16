package org.dreamteam.mafia.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

/**
 * Предоставляет вспомогательные Bean-ы для классов, реализующих аутентификацию и регистрацию
 * Вынесен отдельно во избежание циклических зависимостей между классами
 */
@Configuration
public class SecurityHelperBeansConfiguration {

    /**
     * Возвращает (де-)шифровальщик паролей
     *
     * @return - Шифровальщик для паролей
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Возвращает секретный ключ для подписи JWT
     * Ключ генерируется случайно при каждом запуске приложения.
     * Т. о. JWT, выданные до перезапуска перестают приниматься после перезапуска.
     *
     * @return - секретный ключ
     */
    @Bean
    @Scope("singleton")
    public SecretKey secretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
