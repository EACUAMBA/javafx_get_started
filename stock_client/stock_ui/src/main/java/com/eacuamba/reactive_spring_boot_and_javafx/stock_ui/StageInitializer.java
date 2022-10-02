package com.eacuamba.reactive_spring_boot_and_javafx.stock_ui;

import com.eacuamba.reactive_spring_boot_and_javafx.stock_ui.ChartApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

//Esta é a classe que vai reagir ao ouvir o evento StageReadyEvent, e vai pegar o stage e vamos agora construir a nossa aplicacao.
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    //Pegando um ficheiro como recurso no classpath, nível de classes o nivel mais baixo, com o nome chart.fxml
    @Value("classpath:/chart.fxml")
    private Resource charResource;
    private final String applicationTitle;

    //Criando a variavel com o contexto da aplicação onde os beans serão armazenados.
    private final ApplicationContext applicationContext;

    //Injectando os beans e pegando uma variavel do ficheiro properties utilizando ${}
    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            //Pegando o ficheiro fxml que esta no resource e carregando ele o FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(charResource.getURL());

            //Definindo que a aprtir de agora o controller eu é que irei gerar manualmente, ensinando o fxmlloader a pegar o controller
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));

            //Carregando o primeiro componente do ficheiro fxml que é a vbox
            final Parent parent = fxmlLoader.load();

            //Com o stage em maos podemos adicionar o scene
            final Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 800, 600));
            stage.setTitle(applicationTitle);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
