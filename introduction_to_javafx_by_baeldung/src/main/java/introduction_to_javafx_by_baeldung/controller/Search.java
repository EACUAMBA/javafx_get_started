package introduction_to_javafx_by_baeldung.controller;

import introduction_to_javafx_by_baeldung.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Search {

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private VBox dataContainer;

    @FXML
    private TableView<Person> tableView;
}
