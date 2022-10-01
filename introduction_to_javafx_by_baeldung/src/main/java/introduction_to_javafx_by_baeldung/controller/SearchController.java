package introduction_to_javafx_by_baeldung.controller;

import introduction_to_javafx_by_baeldung.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ObservableList<Person> personObservableList;
    private ObservableList<Person> personSearchResultObservableList;

    @FXML
    private void initialize() {
        this.searchButton.setText("Search");
        this.searchButton.setOnAction(event -> loadData());
        this.searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        personObservableList = FXCollections.observableArrayList();
        personSearchResultObservableList = FXCollections.observableArrayList(this.personObservableList);
        this.initTable();
    }

    private void loadData() {

    }

    public void initTable() {
        this.tableView = new TableView<>(this.personObservableList);
        TableColumn<Person, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));

        TableColumn<Person, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));

        TableColumn<Person, Boolean> isEmployed = new TableColumn<>("Is employed?");
        isEmployed.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("isEmployed"));

        this.tableView.getColumns().addAll(id, name, isEmployed);
        this.dataContainer.getChildren().add(this.tableView);
    }
}
