package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public WebClientStockClient getWebClientStockClient(WebClient webClient){
        return new WebClientStockClient(webClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebClient getWebClient(){
        return WebClient.builder().build();
    }
}
