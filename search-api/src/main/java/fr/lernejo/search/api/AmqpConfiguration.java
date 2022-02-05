package fr.lernejo.search.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.amqp.core.Queue;

@Configuration
public class AmqpConfiguration {
    static final String GAME_INFO_QUEUE = "game_info";

    @Bean
    Queue queue() {
        return new Queue(GAME_INFO_QUEUE, true);
    }
}
