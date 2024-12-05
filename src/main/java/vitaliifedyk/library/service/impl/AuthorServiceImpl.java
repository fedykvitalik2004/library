package vitaliifedyk.library.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vitaliifedyk.library.dto.CreateAuthorDto;
import vitaliifedyk.library.dto.ReadAuthorDto;
import vitaliifedyk.library.exception.NotFoundException;
import vitaliifedyk.library.mapper.AuthorMapper;
import vitaliifedyk.library.model.Author;
import vitaliifedyk.library.repository.AuthorRepository;
import vitaliifedyk.library.service.AuthorService;

import static vitaliifedyk.library.constant.ExceptionConstant.AUTHOR_NOT_FOUND_BY_ID;

@Service
@Transactional
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
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
                .orElseThrow(() -> new  NotFoundException(AUTHOR_NOT_FOUND_BY_ID.formatted(id))));
    }

    @Override
    public ReadAuthorDto updateAuthor(Long id, CreateAuthorDto createAuthorDto) {
        final Author author = authorRepository.findById(id)
                .orElseThrow(() -> new  NotFoundException(AUTHOR_NOT_FOUND_BY_ID.formatted(id)));
        author.setFullName(createAuthorDto.getFullName());
        author.setDescription(createAuthorDto.getDescription());
        return authorMapper.toReadAuthorDto(authorRepository.save(author));
    }

    
}
