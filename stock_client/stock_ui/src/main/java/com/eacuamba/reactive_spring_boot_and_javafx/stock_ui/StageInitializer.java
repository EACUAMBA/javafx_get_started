package com.eacuamba.reactive_spring_boot_and_javafx.stock_ui;

import com.eacuamba.reactive_spring_boot_and_javafx.stock_ui.ChartApplication.StageReadyEvent;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//Esta é a classe que vai reagir ao ouvir o evento StageReadyEvent, e vai pegar o stage e vamos agora construir a nossa aplicacao.
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();

    }
}
