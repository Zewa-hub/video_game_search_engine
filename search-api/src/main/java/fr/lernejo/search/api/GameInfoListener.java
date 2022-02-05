package fr.lernejo.search.api;

import org.apache.lucene.index.IndexReader;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLOutput;

import static fr.lernejo.search.api.AmqpConfiguration.GAME_INFO_QUEUE;

@Component
public class GameInfoListener {
    final RestHighLevelClient restClient;

    public GameInfoListener(RestHighLevelClient restClient) {
        this.restClient = restClient;
    }

    @RabbitListener(queues = GAME_INFO_QUEUE)
    public void onMessage(String message,@Header("game_id") String gameId)  {
        IndexRequest request = new IndexRequest("games");
        request.id(gameId);
        request.source(message, XContentType.JSON);
        try {
            this.restClient.index(request, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
