package vitaliifedyk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitaliifedyk.library.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAndIdNot(String email, Long id);
}