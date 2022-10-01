/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package javafx_get_started;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx_get_started.hellofx.HelloFX;
import javafx_get_started.materialfx.MaterialFXApp;

public class App extends Application {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Integer action = Integer.parseInt(args[0]);
        System.out.println("Running " + action);
        switch (action) {
            case 1: {
                HelloFX.main(args);
                break;
            }
            case 2: {
                MaterialFXApp.main(args);
                break;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }
}
