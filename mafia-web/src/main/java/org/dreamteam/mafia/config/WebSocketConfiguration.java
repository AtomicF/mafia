package org.dreamteam.mafia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Класс для настройки параметров WebSocket
 */
@Configuration
@ComponentScan("org.dreamteam.mafia")
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * Регистрирует точки подключения клиентов Stomp
     *
     * @param registry - реестр точек подключения клиентов Stomp
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-messaging").withSockJS();
    }

    /**
     * Настраивает брокер сообщений
     *
     * @param config - настройки брокера сообщений
     */
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/chat");
        config.setApplicationDestinationPrefixes("/app");
    }
}