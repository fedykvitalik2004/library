package vitaliifedyk.library.service;

import org.springframework.data.jpa.domain.Specification;
import vitaliifedyk.library.dto.CreateBookDto;
import vitaliifedyk.library.dto.ReadBookDto;
import vitaliifedyk.library.model.Book;

import java.util.List;

public interface BookService {
    ReadBookDto createBook(CreateBookDto createBookDto);
    ReadBookDto readBook(Long id);
    ReadBookDto updateBook(Long id, CreateBookDto createBookDto);
    void deleteBook(Long id);
    List<ReadBookDto> search(Specification<Book> specification);
    boolean authorHasBooks(Long authorId);
}
