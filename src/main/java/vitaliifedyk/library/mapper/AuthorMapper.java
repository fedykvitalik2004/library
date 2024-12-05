package vitaliifedyk.library.mapper;

import org.mapstruct.Mapper;
import vitaliifedyk.library.dto.CreateAuthorDto;
import vitaliifedyk.library.dto.ReadAuthorDto;
import vitaliifedyk.library.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toAuthor(CreateAuthorDto createAuthorDto);
    ReadAuthorDto toReadAuthorDto(Author author);
}
