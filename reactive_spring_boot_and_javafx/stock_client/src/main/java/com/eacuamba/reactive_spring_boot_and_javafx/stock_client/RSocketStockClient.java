package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;

@Slf4j
public class RSocketStockClient implements StockClient {

    private RSocketRequester rSocketRequester;

    public RSocketStockClient(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }

    @Override
    public Flux<StockPrice> pricesFor(String symbol){
        return rSocketRequester.route("stockPrices")
                .data(symbol)
                .retrieveFlux(StockPrice.class)
                .retryWhen(Retry.backoff(5, Duration.ofSeconds(20)))
                .doOnError(IOException.class, (e) -> log.error("error", e));
    }
}
