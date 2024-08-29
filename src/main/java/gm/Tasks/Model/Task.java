package gm.Tasks.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Task {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer taskId;
    private String taskName;
    private String responsiblePerson;
    private String status;
}
