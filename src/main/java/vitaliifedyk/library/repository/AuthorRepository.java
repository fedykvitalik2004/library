package vitaliifedyk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitaliifedyk.library.dto.ReadAuthorDto;
import vitaliifedyk.library.model.Author;

import java.awt.print.Pageable;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}