package fr.lernejo.search.api;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GameInfosController {

    private final RestHighLevelClient client;

    public GameInfosController(RestHighLevelClient restClient)
    {
        this.client = restClient;
    }

    @GetMapping("/api/games")
    public Iterable<Object> getMessage(@RequestParam(name = "query") String query) throws IOException {
        SearchRequest request = new SearchRequest("games");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new QueryStringQueryBuilder(query));
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        List<Object> list = new ArrayList();
        for (SearchHit hit : hits)
            list.add(hit.getSourceAsMap());
        return list;
    }
}
