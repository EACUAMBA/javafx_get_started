package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import io.rsocket.core.RSocketClient;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.ClientTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketConnectorConfigurer;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RSocketStockClientIntegrationTest {

    @Autowired
    private RSocketRequester.Builder builder;

    private RSocketRequester createRSocketRequester() {
        return builder.tcp("localhost", 7000);
    }

    @Test
    void shouldRetrieveStockPricesFromTheStockService() {
        //given
        StockClient stockClient = new RSocketStockClient(createRSocketRequester() );

        //when
        Flux<StockPrice> prices = stockClient.pricesFor("symbol");

        //then
        //assertNotNull(prices);
        //Flux<StockPrice> fivePrices = prices.take(5);
        //Assertions.assertEquals(5,   fivePrices.count().block());
        //Assertions.assertEquals("symbol", Objects.requireNonNull(fivePrices.blockFirst()).getSymbol());
        StepVerifier.create(prices.take(4))
                .expectNextMatches(stockPrice -> stockPrice.getSymbol().equals("symbol"))
                .expectNextMatches(stockPrice -> stockPrice.getSymbol().equals("symbol"))
                .expectNextMatches(stockPrice -> stockPrice.getSymbol().equals("symbol"))
                .expectNextMatches(stockPrice -> stockPrice.getSymbol().equals("symbol"))
        .verifyComplete();
    }


}