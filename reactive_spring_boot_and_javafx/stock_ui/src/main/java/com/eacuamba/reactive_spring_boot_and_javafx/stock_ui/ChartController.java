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
public class ChartController implements Consumer<StockPrice> {

    @FXML
    public LineChart<String, BigDecimal> chart;

    private WebClientStockClient webClientStockClient;
    private ObservableList<Data<String, BigDecimal>> seriesData = observableArrayList();

    public ChartController(WebClientStockClient webClientStockClient) {
        this.webClientStockClient = webClientStockClient;
    }

    @FXML
    public void initialize(){
        String symbol = "BTCUSD";
        ObservableList<Series<String, BigDecimal>> data = observableArrayList();
        data.add(new Series<String, BigDecimal>(symbol, seriesData));
        chart.setData(data);

        webClientStockClient.pricesFor(symbol).subscribe(this);
    }

    @Override
    public void accept(StockPrice stockPrice) {
        Platform.runLater(()->{
            this.seriesData.add(new Data<>(valueOf(stockPrice.getTime().getSecond()), stockPrice.getPrice()));
        });
    }
}
