package com.eacuamba.reactive_spring_boot_and_javafx.stock_ui;

import com.eacuamba.reactive_spring_boot_and_javafx.stock_client.StockClient;
import com.eacuamba.reactive_spring_boot_and_javafx.stock_client.StockPrice;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static java.lang.String.valueOf;
import static javafx.collections.FXCollections.observableArrayList;

@Controller
public class ChartController{

    @FXML
    public LineChart<String, BigDecimal> chart;

    private final StockClient stockClient;


    public ChartController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @FXML
    public void initialize(){
        String symbol1 = "BTCUSD";
        PriceSubscriber priceSubscriber1 = new PriceSubscriber(symbol1);
        stockClient.pricesFor(symbol1).subscribe(priceSubscriber1);

        String symbol2 = "BTCNZD";
        PriceSubscriber priceSubscriber2 = new PriceSubscriber(symbol2);
        stockClient.pricesFor(symbol2).subscribe(priceSubscriber2);

        ObservableList<Series<String, BigDecimal>> data = observableArrayList();
        data.add(priceSubscriber1.getSeries());
        data.add(priceSubscriber2.getSeries());
        chart.setData(data);
    }

    private static class PriceSubscriber implements Consumer<StockPrice> {
        private ObservableList<Data<String, BigDecimal>> seriesData = observableArrayList();
        private Series<String, BigDecimal> series;
        private String symbol;

        public PriceSubscriber(String symbol) {
            this.symbol = symbol;
            this.series = new Series<>(symbol, seriesData);
        }

        @Override
        public void accept(StockPrice stockPrice) {
            Platform.runLater(()->{
                this.seriesData.add(new Data<>(valueOf(stockPrice.getTime().getSecond()), stockPrice.getPrice()));
            });
        }

        public Series<String, BigDecimal> getSeries(){
            return this.series;
        }

    }
}
