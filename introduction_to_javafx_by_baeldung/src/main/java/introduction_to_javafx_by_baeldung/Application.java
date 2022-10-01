package introduction_to_javafx_by_baeldung;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    //This is the entry point of this application, everything at start time goes here.
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/SearchController.fxml"));
        AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Title of this stage.");
        stage.setScene(scene);
        stage.show();
    }
}
