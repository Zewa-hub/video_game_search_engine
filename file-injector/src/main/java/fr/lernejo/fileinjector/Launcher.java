package fr.lernejo.fileinjector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) throws Exception {
        if (args.length != 1)
            throw new IOException("1 argument expected but 0 was given");
        try (AbstractApplicationContext springContext = new AnnotationConfigApplicationContext(Launcher.class)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            List<GameMap> collection = Arrays.asList(mapper.readValue(new File(args[0]),GameMap[].class));
            RabbitTemplate rt = springContext.getBean(RabbitTemplate.class);
            rt.setMessageConverter(new Jackson2JsonMessageConverter());
            for (GameMap message : collection) {
                rt.convertAndSend("", "game_infos", message, m -> {
                    m.getMessageProperties().getHeaders().put("game_id", message.id);
                    return m;
                });
            }
        }
    }
}
