package vitaliifedyk.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vitaliifedyk.library.dto.CreateBorrowedBookDto;
import vitaliifedyk.library.model.Book;
import vitaliifedyk.library.model.BorrowedBook;
import vitaliifedyk.library.dto.ReadBorrowedBookDto;

@Mapper(componentModel = "spring")
public interface BorrowedBookMapper {
    BorrowedBook toBorrowedBook(CreateBorrowedBookDto createBorrowedBookDto);
    @Mapping(target = "book.authorId", source = "book.author.id")
    @Mapping(target = "book.authorFullName", source = "book.author.fullName")
    @Mapping(target = "book.genre", source = "book.genre")
    @Mapping(target = "book.pagesCount", source = "book.pagesCount")
    @Mapping(target = "book.title", source = "book.title")
    @Mapping(target = "book.id", source = "book.id")
    ReadBorrowedBookDto toBorrowedBookDto(BorrowedBook borrowedBook, Book book);
}
