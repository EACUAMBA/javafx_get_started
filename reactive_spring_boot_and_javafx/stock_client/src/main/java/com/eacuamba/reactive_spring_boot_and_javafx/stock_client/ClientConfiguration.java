package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    @Profile("sse")
    public WebClientStockClient getWebClientStockClient(WebClient webClient){
        return new WebClientStockClient(webClient);
    }

    @Bean
    @Profile("rsocket")
    public RSocketStockClient getRSocketStockClient(RSocketRequester requester){
        return new RSocketStockClient(requester);
    }

    @Bean
    @ConditionalOnMissingBean
    public RSocketRequester getRSocketRequester(RSocketRequester.Builder builder){
        return builder.tcp("localhost", 7000);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient getWebClient(){
        return WebClient.builder().build();
    }
}
