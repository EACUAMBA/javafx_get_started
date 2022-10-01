package introduction_to_javafx_by_baeldung.controller;

import introduction_to_javafx_by_baeldung.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SearchController {

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private VBox dataContainer;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private void initialize(){
        this.searchButton.setText("Search");
        this.searchButton.setOnAction(event -> loadData());
        this.searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
        this.initTable();
    }

    private void loadData(){

    }

    public void initTable(){
        this.tableView = new TableView<>();
        TableColumn<Person, Integer> id = new TableColumn<>("ID");
        TableColumn<Person, String> name = new TableColumn<>("Name");
        TableColumn<Person, Boolean> isEmployed = new TableColumn<>("Is employed?");
        this.tableView.getColumns().addAll(id, name, isEmployed);
    this.dataContainer.getChildren().add(this.tableView);
    }
}
