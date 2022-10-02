package introduction_to_javafx_by_baeldung.controller;

import introduction_to_javafx_by_baeldung.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        this.searchField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                loadData();
            }
        });

        List<Person> people = this.buildPeople(10);

        personObservableList = FXCollections.observableArrayList(people);
        personSearchResultObservableList = FXCollections.observableArrayList(this.personObservableList);
        this.initTable();
    }

    private void loadData() {
        final String filters = this.searchField.getText();
        Task<ObservableList<Person>> observableListTask = new Task<ObservableList<Person>>() {
            @Override
            protected ObservableList<Person> call() throws Exception {
                this.updateMessage("Working...");
                return FXCollections.observableArrayList(personObservableList.stream()
                        .filter(p-> p.getName().toLowerCase().contains(filters.toLowerCase()))
                        .collect(Collectors.toList()));
            }
        };

        observableListTask.setOnSucceeded(event -> {
            ObservableList<Person> observableListTaskValue = observableListTask.getValue();
            this.tableView.setItems(observableListTaskValue);
        });

        Thread thread = new Thread(observableListTask);
        thread.setDaemon(Boolean.TRUE);
        thread.start();
    }

    public void initTable() {
        this.tableView = new TableView<>(this.personObservableList);
        TableColumn<Person, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));

        TableColumn<Person, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));

        TableColumn<Person, String> isEmployed = new TableColumn<>("Is employed?");
        isEmployed.setCellValueFactory(new PropertyValueFactory<Person, String>("employedStatus"));

        this.tableView.getColumns().addAll(id, name, isEmployed);
        this.dataContainer.getChildren().add(this.tableView);
    }

    public List<Person> buildPeople(int quantity){
        ArrayList<Person> people = new ArrayList<>();
        for (int i= 0; i < quantity; i++ ){
            people.add(Person.getFAKEInstance());
        }

        return people;
    }
}
