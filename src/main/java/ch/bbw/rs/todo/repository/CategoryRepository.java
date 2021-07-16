package ch.bbw.rs.todo.repository;

import ch.bbw.rs.todo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
