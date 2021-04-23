package kom.demo.backend.todoappdemobackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String task;

    @Column
    private Boolean isDone;

    @Builder
    public TodoItem(String task, Boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public void update(String task, Boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }
}
