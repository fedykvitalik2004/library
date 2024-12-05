package vitaliifedyk.library.service;

import vitaliifedyk.library.dto.CreateAuthorDto;
import vitaliifedyk.library.dto.ReadAuthorDto;

public interface AuthorService {
    ReadAuthorDto createAuthor(CreateAuthorDto createAuthorDto);

    ReadAuthorDto readAuthor(Long id);

    ReadAuthorDto updateAuthor(Long id, CreateAuthorDto readAuthorDto);
}
