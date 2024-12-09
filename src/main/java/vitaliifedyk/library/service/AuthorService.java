package vitaliifedyk.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vitaliifedyk.library.dto.CreateAuthorDto;
import vitaliifedyk.library.dto.ReadAuthorDto;


public interface AuthorService {
    ReadAuthorDto createAuthor(CreateAuthorDto createAuthorDto);

    ReadAuthorDto readAuthor(Long id);

    ReadAuthorDto updateAuthor(Long id, CreateAuthorDto readAuthorDto);

    void deleteAuthor(Long id);

    Page<ReadAuthorDto> findAll(Pageable pageable);
}
