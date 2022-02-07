package fr.lernejo.search.api;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class GameInfoListenerTest {
    @Test
    void onValidMessage() {
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            RabbitTemplate rt = springContext.getBean(RabbitTemplate.class);
            assertAll(() -> rt.convertAndSend("", "game_info", "{\"message\":\"Test\"}" , m -> {
                m.getMessageProperties().getHeaders().put("game_id", "1024");
                return m;
            }));
        }
    }
}
