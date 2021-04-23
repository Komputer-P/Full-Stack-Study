package kom.demo.backend.todoappdemobackend.repository;
import kom.demo.backend.todoappdemobackend.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long> {

}
