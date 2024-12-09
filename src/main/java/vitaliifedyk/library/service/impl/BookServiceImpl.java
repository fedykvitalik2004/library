package vitaliifedyk.library.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vitaliifedyk.library.constant.ExceptionConstants;
import vitaliifedyk.library.dto.CreateBookDto;
import vitaliifedyk.library.dto.ReadBookDto;
import vitaliifedyk.library.exception.NotFoundException;
import vitaliifedyk.library.exception.OperationNotPermittedException;
import vitaliifedyk.library.mapper.BookMapper;
import vitaliifedyk.library.model.Author;
import vitaliifedyk.library.model.Book;
import vitaliifedyk.library.repository.AuthorRepository;
import vitaliifedyk.library.repository.BookRepository;
import vitaliifedyk.library.service.BookService;
import vitaliifedyk.library.service.BorrowedBookService;

import java.util.List;

import static vitaliifedyk.library.constant.ExceptionConstants.BOOK_CANNOT_BE_DELETED;
import static vitaliifedyk.library.constant.ExceptionConstants.BOOK_NOT_FOUND_BY_ID;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private AuthorRepository authorRepository;
    private BorrowedBookService borrowedBookService;
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Override
    public ReadBookDto createBook(final CreateBookDto createBookDto) {
        final Author author = authorRepository.findById(createBookDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.AUTHOR_NOT_FOUND_BY_ID
                        .formatted(createBookDto.getAuthorId())));
        Book book = bookMapper.toBook(createBookDto);
        book.setAuthor(author);
        return bookMapper.toReadBookDto(bookRepository.save(book));
    }

    @Override
    public ReadBookDto readBook(final Long id) {
        return bookMapper.toReadBookDto(bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_BY_ID.formatted(id))));
    }

    @Override
    public ReadBookDto updateBook(final Long id, final CreateBookDto createBookDto) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_BY_ID.formatted(id)));
        book.setTitle(createBookDto.getTitle());
        book.setDescription(createBookDto.getDescription());
        if(!book.getAuthor().getId().equals(createBookDto.getAuthorId())) {
            final Author author = authorRepository.findById(createBookDto.getAuthorId())
                    .orElseThrow(() -> new NotFoundException(ExceptionConstants.AUTHOR_NOT_FOUND_BY_ID
                            .formatted(createBookDto.getAuthorId())));
            book.setAuthor(author);
        }
        return bookMapper.toReadBookDto(book);
    }

    @Override
    public void deleteBook(final Long id) {
        if (borrowedBookService.isBorrowedByBook(id)) {
            throw new OperationNotPermittedException(BOOK_CANNOT_BE_DELETED);
        }
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_BY_ID.formatted(id)));
        bookRepository.delete(book);
    }

    @Override
    public List<ReadBookDto> search(final Specification<Book> specification) {
        return bookRepository.findAll(specification).stream().map(o -> bookMapper.toReadBookDto(o))
                .toList();
    }

    @Override
    public boolean authorHasBooks(Long authorId) {
        return bookRepository.existsByAuthorId(authorId);
    }
}