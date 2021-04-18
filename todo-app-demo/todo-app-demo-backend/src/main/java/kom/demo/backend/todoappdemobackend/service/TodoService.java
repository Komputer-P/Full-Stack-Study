package kom.demo.backend.todoappdemobackend.service;

import kom.demo.backend.todoappdemobackend.domain.TodoItem;
import kom.demo.backend.todoappdemobackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepo;

    public List<TodoItem> fetchAllTodoItems() {
        return todoRepo.fetchAllTodoItems();
    }

    public TodoItem updateTodoItem(Integer id, TodoItem todoItem) {
        Optional<TodoItem> todoOpt = todoRepo.fetchAllTodoItems()
                .stream()
                .filter(item -> item.getId().equals(id))
                .findAny();

        if(todoOpt.isPresent()) {
            TodoItem item = todoOpt.get();
            item.setIsDone(todoItem.getIsDone());
            item.setTask(todoItem.getTask());

            return item;
        }

        return null;
    }

    public TodoItem createTodoItem() {
        TodoItem todoItem = new TodoItem();

        todoItem.setIsDone(false);
        todoItem = todoRepo.save(todoItem);
        todoItem.setTask("Task #" + todoItem.getId());

        return todoItem;
    }

    public void deleteTodoItem(Integer id) {
        todoRepo.delete(id);
    }
}
