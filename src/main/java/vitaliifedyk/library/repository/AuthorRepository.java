package vitaliifedyk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitaliifedyk.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
