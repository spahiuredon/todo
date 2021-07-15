package ch.bbw.rs.todo.repository;

import ch.bbw.rs.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
