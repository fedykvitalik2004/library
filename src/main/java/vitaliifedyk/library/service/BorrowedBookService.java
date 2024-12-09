package vitaliifedyk.library.service;

import vitaliifedyk.library.dto.CreateBorrowedBookDto;
import vitaliifedyk.library.dto.ReadBorrowedBookDto;

import java.util.List;

public interface BorrowedBookService {
    ReadBorrowedBookDto add(CreateBorrowedBookDto createBorrowedBookDto);
    boolean isBorrowedByUser(Long userId);
    boolean isBorrowedByBook(Long bookId);
    void remove(Long bookId, Long userId);
    List<ReadBorrowedBookDto> getBorrowedBooksForUser(Long userId);
}
