package vitaliifedyk.library.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vitaliifedyk.library.dto.CreateBookDto;
import vitaliifedyk.library.dto.ReadBookDto;
import vitaliifedyk.library.model.Book;
import vitaliifedyk.library.search.BookSpecification;
import vitaliifedyk.library.search.SpecificationsBuilder;
import vitaliifedyk.library.service.BookService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadBookDto createBook(@RequestBody @Valid final CreateBookDto createBookDto) {
        return bookService.createBook(createBookDto);
    }

    @PutMapping("/{id}")
    public ReadBookDto updateBook(@PathVariable final Long id, @RequestBody @Valid final CreateBookDto createBookDto) {
        return bookService.updateBook(id, createBookDto);
    }

    @GetMapping("/{id}")
    public ReadBookDto getBook(@PathVariable final Long id) {
        return bookService.readBook(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable final Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public List<ReadBookDto> searchBook(@RequestParam final String query) {
        final SpecificationsBuilder<Book> builder = new SpecificationsBuilder<>();
        final Pattern pattern = Pattern.compile("(\\w+?)(:|!:|<|>|<=|>=|contains)(\\w+?),");
        final Matcher matcher = pattern.matcher(query + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return bookService.search(builder.build(BookSpecification::new));
    }
}