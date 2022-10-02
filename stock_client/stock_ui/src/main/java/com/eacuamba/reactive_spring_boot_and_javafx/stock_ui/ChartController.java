package com.eacuamba.reactive_spring_boot_and_javafx.stock_ui;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import org.springframework.stereotype.Controller;

@Controller
public class ChartController {

    @FXML
    public LineChart<String, Double> chart;

    @FXML
    public void initialize(){

    }
}
