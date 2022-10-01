package com.eacuamba.reactive_spring_boot_and_javafx.stock_ui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

//A classe que inicializa o stage;
//Utilizamos o padrão observale para observamos pela criação do do stage e somente aí que construimos o nosso motor spring.
public class ChartApplication extends Application {
    //Criamos a nossa aplicaton context que vamos configurar manualmente.
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) throws Exception {
        //Vamos [ublicar um event assim que o stage estiver pronto para ser utilizado, aqui utilizamos o publicador de eventos do spring.
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() throws Exception {
        //Estamos criando o nosso contexto da aplicação, este metodo é executado antes do start.
        applicationContext = new SpringApplicationBuilder(StockUiApplication.class).run();
    }

    //Criamos uma classe que extend a AppEvent do spring com o objectivo de se tornar um evento, este evento recebe  um Stage quando for criado.
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        //Metodo que retorna o evento.
        public Stage getStage() {
            return ((Stage) this.getSource());
        }
    }
}
