package com.eacuamba.reactive_spring_boot_and_javafx.stock_ui;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockUiApplication {

    //Mudamos o inicializador da aplicação para não mais ser o spring boot padrão mas agora ser uma aplicação javaFX.
    //Agora ao inicializar a aplicação ela vai não mais vai utilizar o spring, mas sim o metodo estatico da classe abstracta Application do JavaFX e passamoscomo parametro a classe que inicializa o stage da nossa aplicação JavaFX e os argumentos.
    public static void main(String[] args) {
        Application.launch(ChartApplication.class, args);
    }

}
