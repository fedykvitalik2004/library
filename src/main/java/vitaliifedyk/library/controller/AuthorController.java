package vitaliifedyk.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vitaliifedyk.library.dto.CreateAuthorDto;
import vitaliifedyk.library.dto.ReadAuthorDto;
import vitaliifedyk.library.service.AuthorService;

@RestController
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadAuthorDto createAuthor(@RequestBody final CreateAuthorDto createAuthorDto) {
        return authorService.createAuthor(createAuthorDto);
    }

    @GetMapping("/{id}")
    public ReadAuthorDto readAuthor(@PathVariable final Long id) {
        return authorService.readAuthor(id);
    }
}
