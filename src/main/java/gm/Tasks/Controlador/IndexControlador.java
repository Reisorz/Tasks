package gm.Tasks.Controlador;

import gm.Tasks.Model.Task;
import gm.Tasks.Service.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexControlador implements Initializable {

    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, Integer> taskIdColumn;

    @FXML
    private TableColumn<Task, String> taskNameColumn;

    @FXML
    private TableColumn<Task, String> responsiblePersonColumn;

    @FXML
    private TableColumn<Task, String> statusColumn;

    private final ObservableList<Task> taskList =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configColumns();
        listTasks();
    }

    private  void configColumns() {
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        responsiblePersonColumn.setCellValueFactory(new PropertyValueFactory<>("responsiblePerson"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private  void listTasks() {
        logger.info("Listing tasks");
        taskList.clear();
        taskList.addAll(taskService.listTasks());
        taskTable.setItems(taskList);
    }
}

