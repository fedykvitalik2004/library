package vitaliifedyk.library.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vitaliifedyk.library.dto.CreateAuthorDto;
import vitaliifedyk.library.dto.ReadAuthorDto;
import vitaliifedyk.library.service.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadAuthorDto createAuthor(@RequestBody @Valid final CreateAuthorDto createAuthorDto) {
        return authorService.createAuthor(createAuthorDto);
    }

    @GetMapping("/{id}")
    public ReadAuthorDto readAuthor(@PathVariable final Long id) {
        return authorService.readAuthor(id);
    }

    @PutMapping("/{id}")
    public ReadAuthorDto updateAuthor(@PathVariable final Long id,
                                      @RequestBody @Valid final CreateAuthorDto createAuthorDto) {
        return authorService.updateAuthor(id, createAuthorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable final Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping
    public Page<ReadAuthorDto> readAuthors(final Pageable pageable) {
        return authorService.findAll(pageable);
    }
}
