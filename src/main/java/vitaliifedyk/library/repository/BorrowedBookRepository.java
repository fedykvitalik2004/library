package vitaliifedyk.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vitaliifedyk.library.model.BorrowedBook;
import vitaliifedyk.library.model.BorrowedBookId;

import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, BorrowedBookId> {
    boolean existsByUserId(long userId);
    boolean existsByBookId(long bookId);
    @Query("SELECT bb FROM BorrowedBook bb JOIN FETCH Book b ON bb.bookId = b.id WHERE bb.userId = :userId")
    List<BorrowedBook> findByUserId(long userId);
}