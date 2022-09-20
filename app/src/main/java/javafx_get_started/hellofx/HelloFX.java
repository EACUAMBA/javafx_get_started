package javafx_get_started.hellofx;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.converter.CurrencyStringConverter;

public class HelloFX extends Application {
  public static void main(String[] args) {
    Application.launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    Locale.setDefault(new Locale("pt", "MZ"));
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    
    l.setTextAlignment(TextAlignment.CENTER);
    TextField nameTextField = new TextField();
    Group group = new Group(l, nameTextField);
    Scene scene = new Scene(new StackPane(group), 640, 480);
    stage.setScene(scene);
    stage.show();
  }
}
