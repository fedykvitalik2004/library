package vitaliifedyk.library.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vitaliifedyk.library.dto.CreateBorrowedBookDto;
import vitaliifedyk.library.dto.ReadBorrowedBookDto;
import vitaliifedyk.library.exception.NotFoundException;
import vitaliifedyk.library.exception.OperationNotPermittedException;
import vitaliifedyk.library.mapper.BorrowedBookMapper;
import vitaliifedyk.library.model.Book;
import vitaliifedyk.library.model.BorrowedBook;
import vitaliifedyk.library.model.BorrowedBookId;
import vitaliifedyk.library.repository.BookRepository;
import vitaliifedyk.library.repository.BorrowedBookRepository;
import vitaliifedyk.library.service.BorrowedBookService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static vitaliifedyk.library.constant.ExceptionConstants.*;

@Service
@AllArgsConstructor
public class BorrowedBookServiceImpl implements BorrowedBookService {
    private BookRepository bookRepository;
    private BorrowedBookRepository borrowedBookRepository;
    private BorrowedBookMapper borrowedBookMapper;

    @Override
    public ReadBorrowedBookDto add(CreateBorrowedBookDto createBorrowedBookDto) {
        if (borrowedBookRepository.existsById(new BorrowedBookId(createBorrowedBookDto.getBookId(),
                createBorrowedBookDto.getUserId()))) {
            throw new OperationNotPermittedException(BORROWED_BOOK_ALREADY_EXISTS);
        }
        final BorrowedBook borrowedBook = borrowedBookMapper.toBorrowedBook(createBorrowedBookDto);
        final Book book = bookRepository.findById(createBorrowedBookDto.getBookId())
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_BY_ID.formatted(createBorrowedBookDto.getBookId())));
        borrowedBook.setBorrowDate(ZonedDateTime.now());
        return borrowedBookMapper.toBorrowedBookDto(borrowedBookRepository.save(borrowedBook), book);
    }

    @Override
    public boolean isBorrowedByUser(final Long userId) {
        return borrowedBookRepository.existsByUserId(userId);
    }

    @Override
    public boolean isBorrowedByBook(final Long bookId) {
        return borrowedBookRepository.existsByBookId(bookId);
    }

    @Override
    public void remove(final Long bookId, final Long userId) {
        final BorrowedBook borrowedBook = borrowedBookRepository.findById(new BorrowedBookId(bookId, userId))
                .orElseThrow(() -> new NotFoundException(BORROWED_BOOK_NOT_FOUND));
        borrowedBookRepository.delete(borrowedBook);
    }

    @Override
    public List<ReadBorrowedBookDto> getBorrowedBooksForUser(final Long userId) {
        final List<BorrowedBook> borrowedBooks = borrowedBookRepository.findByUserId(userId)
                .stream().toList();
        final List<Book> books = bookRepository.findAllByIds(borrowedBooks.stream()
                .map(BorrowedBook::getBookId).toList());
        final List<ReadBorrowedBookDto> readBorrowedBooks= new ArrayList<>();
        for ( int i = 0; i < borrowedBooks.size(); i++ ) {
            readBorrowedBooks.add(borrowedBookMapper.toBorrowedBookDto(borrowedBooks.get(i), books.get(i)));
        }
        return readBorrowedBooks;
    }
}
