package com.basketball.basketball.config;

/*@Configuration
@EnableElasticsearchRepositories(basePackages = "*")
public class ElasticsearchClientConfig {
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}*/