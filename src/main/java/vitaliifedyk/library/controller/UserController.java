package vitaliifedyk.library.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vitaliifedyk.library.dto.CreateUserDto;
import vitaliifedyk.library.dto.ReadUserDto;
import vitaliifedyk.library.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ReadUserDto addUser(@RequestBody @Valid final CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @GetMapping("/{id}")
    public ReadUserDto getUser(@PathVariable final Long id) {
        return userService.readUser(id);
    }

    @PutMapping("/{id}")
    public ReadUserDto updateUser(@PathVariable final Long id, @RequestBody @Valid final CreateUserDto createUserDto) {
        return userService.updateUser(id, createUserDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable final Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public Page<ReadUserDto> readUsers(final Pageable pageable) {
        return userService.findAll(pageable);
    }
}
