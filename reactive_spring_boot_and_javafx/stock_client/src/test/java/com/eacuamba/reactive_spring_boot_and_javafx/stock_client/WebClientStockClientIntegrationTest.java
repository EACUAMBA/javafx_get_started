package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class WebClientStockClientIntegrationTest {
    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPricesFromTheStockService() {
        //given
        StockClient stockClient = new WebClientStockClient(webClient);

        //when
        Flux<StockPrice> prices = stockClient.pricesFor("symbol");

        //then
        assertNotNull(prices);
        Flux<StockPrice> fivePrices = prices.take(5);
        Assertions.assertEquals(5,   fivePrices.count().block());
        Assertions.assertEquals("symbol", Objects.requireNonNull(fivePrices.blockFirst()).getSymbol());
    }
}