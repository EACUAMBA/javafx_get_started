package javafx_get_started.materialfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MaterialFXApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // TODO Auto-generated method stub
    Button button = new Button("Comprar");
    Scene scene = new Scene(new BorderPane(button), 500, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    MaterialFXApp.launch(args);
  }
  
}
