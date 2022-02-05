package fr.lernejo.search.api;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

    @Bean
    RestHighLevelClient restClient(@Value("${elasticsearch.host:localhost}") String host,
                                   @Value("${elasticsearch.port:9200}") int port,
                                   @Value("${elasticsearch.username:elastic}") String username,
                                   @Value("${elasticsearch.password:admin}") String password)
    {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        RestClientBuilder build = RestClient.builder(new HttpHost(host,port));
        build.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(build);
    }
}
