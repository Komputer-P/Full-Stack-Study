package kom.demo.backend.todoappdemobackend.service;

import kom.demo.backend.todoappdemobackend.domain.TodoItem;
import kom.demo.backend.todoappdemobackend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public List<TodoItem> fetchAllTodoItems() {
        return todoRepository.findAll();
    }

    @Transactional
    public TodoItem updateTodoItem(Long id, TodoItem todoItem) {
        TodoItem updatedTodoItem = findById(id);
        updatedTodoItem.update(todoItem.getTask(),todoItem.getIsDone());

        return updatedTodoItem;
    }

    @Transactional
    public TodoItem createTodoItem() {
        return todoRepository.save(new TodoItem("Task",false));
    }

    @Transactional
    public void deleteTodoItem(Long id) {
        TodoItem deleteTodoItem = findById(id);

        todoRepository.delete(deleteTodoItem);
    }

    public TodoItem findById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 Id를 가진 데이터가 없습니다!"));
    }
}
