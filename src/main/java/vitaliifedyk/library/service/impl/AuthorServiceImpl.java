package vitaliifedyk.library.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vitaliifedyk.library.dto.CreateAuthorDto;
import vitaliifedyk.library.dto.ReadAuthorDto;
import vitaliifedyk.library.exception.NotFoundException;
import vitaliifedyk.library.exception.OperationNotPermittedException;
import vitaliifedyk.library.mapper.AuthorMapper;
import vitaliifedyk.library.model.Author;
import vitaliifedyk.library.repository.AuthorRepository;
import vitaliifedyk.library.service.AuthorService;
import vitaliifedyk.library.service.BookService;

import static vitaliifedyk.library.constant.ExceptionConstants.AUTHOR_CANNOT_BE_DELETED;
import static vitaliifedyk.library.constant.ExceptionConstants.AUTHOR_NOT_FOUND_BY_ID;

@Service
@Transactional
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final BookService bookService;
    private AuthorMapper authorMapper;
    private AuthorRepository authorRepository;

    @Override
    public ReadAuthorDto createAuthor(final CreateAuthorDto createAuthorDto) {
        final Author author = authorMapper.toAuthor(createAuthorDto);
        return authorMapper.toReadAuthorDto(authorRepository.save(author));
    }

    @Override
    public ReadAuthorDto readAuthor(final Long id) {
        return authorMapper.toReadAuthorDto(authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_BY_ID.formatted(id))));
    }

    @Override
    public ReadAuthorDto updateAuthor(final Long id, final CreateAuthorDto createAuthorDto) {
        final Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_BY_ID.formatted(id)));
        author.setFullName(createAuthorDto.getFullName());
        author.setDescription(createAuthorDto.getDescription());
        return authorMapper.toReadAuthorDto(authorRepository.save(author));
    }

    @Override
    public void deleteAuthor(final Long id) {
        final boolean authorExists = authorRepository.existsById(id);
        if (!authorExists) {
            throw new NotFoundException(AUTHOR_NOT_FOUND_BY_ID.formatted(id));
        }
        if (bookService.authorHasBooks(id)) {
            throw new OperationNotPermittedException(AUTHOR_CANNOT_BE_DELETED);
        }
        authorRepository.deleteById(id);
    }

    @Override
    public Page<ReadAuthorDto> findAll(final Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(authorMapper::toReadAuthorDto);
    }
}