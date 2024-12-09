package vitaliifedyk.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vitaliifedyk.library.dto.CreateBookDto;
import vitaliifedyk.library.dto.ReadBookDto;
import vitaliifedyk.library.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "pagesCount", source = "pagesCount")
    Book toBook(CreateBookDto createBookDto);
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorFullName", source = "author.fullName")
    @Mapping(target = "pagesCount", source = "pagesCount")
    ReadBookDto toReadBookDto(Book book);
}
