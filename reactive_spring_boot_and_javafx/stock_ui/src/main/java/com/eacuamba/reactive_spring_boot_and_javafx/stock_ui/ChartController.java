package com.eacuamba.reactive_spring_boot_and_javafx.stock_ui;

import com.eacuamba.reactive_spring_boot_and_javafx.stock_client.WebClientStockClient;
import com.eacuamba.reactive_spring_boot_and_javafx.stock_client.StockPrice;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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

    private WebClientStockClient webClientStockClient;


    public ChartController(WebClientStockClient webClientStockClient) {
        this.webClientStockClient = webClientStockClient;
    }

    @FXML
    public void initialize(){
        String symbol1 = "BTCUSD";
        PriceSubcriber priceSubcriber1 = new PriceSubcriber(symbol1);
        webClientStockClient.pricesFor(symbol1).subscribe(priceSubcriber1);

        String symbol2 = "BTCNZD";
        PriceSubcriber priceSubcriber2 = new PriceSubcriber(symbol2);
        webClientStockClient.pricesFor(symbol2).subscribe(priceSubcriber2);

        ObservableList<Series<String, BigDecimal>> data = observableArrayList();
        data.add(priceSubcriber1.getSeries());
        data.add(priceSubcriber2.getSeries());
        chart.setData(data);
    }

    private static class PriceSubcriber implements Consumer<StockPrice> {
        private ObservableList<Data<String, BigDecimal>> seriesData = observableArrayList();
        private Series<String, BigDecimal> series;
        private String symbol;

        public PriceSubcriber(String symbol) {
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
