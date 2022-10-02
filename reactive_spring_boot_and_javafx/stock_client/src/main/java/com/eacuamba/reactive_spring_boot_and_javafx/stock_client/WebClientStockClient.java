package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;

@Log4j2
public class WebClientStockClient {

    private WebClient webClient;

    public WebClientStockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<StockPrice> pricesFor(String symbol) {
       return webClient.get()
                .uri("http://localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retryWhen(Retry.backoff(5, Duration.ofSeconds(20)))
                .doOnError(IOException.class, log::error);

    }
}
