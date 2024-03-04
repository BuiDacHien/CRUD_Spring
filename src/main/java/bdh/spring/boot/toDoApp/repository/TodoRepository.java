package bdh.spring.boot.toDoApp.repository;

import bdh.spring.boot.toDoApp.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Page<Todo> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageable);
}
