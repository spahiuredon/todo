package ch.bbw.rs.todo.repository;

import ch.bbw.rs.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
