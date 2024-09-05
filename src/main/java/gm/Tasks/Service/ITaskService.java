package gm.Tasks.Service;

import gm.Tasks.Model.Task;
import java.util.List;

public interface ITaskService {
    public List<Task> listTasks();

    public Task searchTaskById(Integer taskId);

    public List<Task> searchTaskByName (String taskName);

    public void saveTask(Task task);

    public void deleteTask(Task task);


}
