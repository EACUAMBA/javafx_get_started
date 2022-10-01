package com.eacuamba.reactive_spring_boot_and_javafx.stock_client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPrice {
    private String symbol;
    private BigDecimal price;
    private LocalDateTime time;
}
