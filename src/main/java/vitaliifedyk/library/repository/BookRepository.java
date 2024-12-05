package vitaliifedyk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitaliifedyk.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
