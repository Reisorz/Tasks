package gm.Tasks.Repository;

import gm.Tasks.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query( value = "SELECT * FROM task WHERE task_name LIKE %:taskName%", nativeQuery = true)
    List<Task> searchTaskByName(@Param("taskName") String taskName);

}