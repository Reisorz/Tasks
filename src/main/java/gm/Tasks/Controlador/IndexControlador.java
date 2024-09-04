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

    @FXML
    public TextField taskNameText;

    @FXML
    public TextField responsiblePersonText;

    @FXML
    public TextField statusText;

    @FXML
    public Button addButton;

    @FXML
    public Button updateButton;

    @FXML
    public Button deleteButton;

    @FXML
    public Button resetButton;

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

    public void addTask() {
        if(taskNameText.getText().isEmpty()) {
            showMessage("Validation Error", "You must give a task name.");
            taskNameText.requestFocus();
            return;
        }
        else {
            Task task = new Task();
            getFormData(task);
            taskService.saveTask(task);
            showMessage("Information", "Task added.");
            resetForm();
            listTasks();
        }
    }

    public void getFormData(Task task) {
        task.setTaskName(taskNameText.getText());
        task.setResponsiblePerson(responsiblePersonText.getText());
        task.setStatus(statusText.getText());

    }

    public void resetForm() {
        taskNameText.clear();
        responsiblePersonText.clear();
        statusText.clear();
    }

    public void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

