package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import reactor.core.publisher.Flux;

public interface StockClient {
    Flux<StockPrice> pricesFor(String symbol);
}
