package kom.demo.backend.todoappdemobackend.web;

import kom.demo.backend.todoappdemobackend.domain.TodoItem;
import kom.demo.backend.todoappdemobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    // Front-End        Java-Server
    // HttpRequest ---> Controller ---> Service ---> Repository
    // Front-End   <--- Controller <--- Service <---

    // fetch all todo_items (from database);

    // CRUD
    // Create   = POST      = 'http://localhost:8080/api/domainObjectName'
    // Read     = GET       = 'http://localhost:8080/api/domainObjectName' OR 'http://localhost:8080/api/domainObjectName/{id}'
    // Update   = PUT       = 'http://localhost:8080/api/domainObjectName/{id}'
    // Delete   = DELETE    = 'http://localhost:8080/api/domainObjectName/{id}'

    @Autowired
    private TodoService todoService;

    @GetMapping("/api/todoItems")
    public ResponseEntity<?> fetchAllTodoItems() {
        List<TodoItem> todoItems = todoService.fetchAllTodoItems();

        return ResponseEntity.status(HttpStatus.OK).body(todoItems);
    }

    @PostMapping("/api/todoItems")
    public ResponseEntity<?> createNewTodoItem() {
        TodoItem todoItem = todoService.createTodoItem();

        return ResponseEntity.status(HttpStatus.OK).body(todoItem);
    }

    @PutMapping("/api/todoItems/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable Integer id, @RequestBody TodoItem todoItem) {
        // call the service
        // get the data back from server
        TodoItem updatedTodoItem = todoService.updateTodoItem(id, todoItem);

        // send it! (back to front end)
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodoItem);
    }

    @DeleteMapping("/api/todoItems/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable Integer id) {

        todoService.deleteTodoItem(id);

        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }
}
