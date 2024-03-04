package bdh.spring.boot.toDoApp.repository;

import bdh.spring.boot.toDoApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
