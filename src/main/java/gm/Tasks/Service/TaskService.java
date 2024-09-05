package gm.Tasks.Service;

import gm.Tasks.Model.Task;
import gm.Tasks.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task searchTaskById(Integer taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        return task;
    }

    @Override
    public List<Task> searchTaskByName(String taskName) {
        return taskRepository.searchTaskByName(taskName);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
