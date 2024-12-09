package vitaliifedyk.library.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vitaliifedyk.library.dto.CreateBorrowedBookDto;
import vitaliifedyk.library.service.BorrowedBookService;
import vitaliifedyk.library.dto.ReadBorrowedBookDto;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/borrowed-books")
@AllArgsConstructor
public class BorrowedBookController {
    private BorrowedBookService borrowedBookService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ReadBorrowedBookDto add(@RequestBody @Valid CreateBorrowedBookDto createBorrowedBookDto) {
        return borrowedBookService.add(createBorrowedBookDto);
    }

    @DeleteMapping("/{bookId}/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBorrowedBook (@PathVariable long bookId, @PathVariable long userId) {
        borrowedBookService.remove(bookId, userId);
    }

    @GetMapping("/user/{userId}")
    public List<ReadBorrowedBookDto> findBorrowedBooksByUserId(@PathVariable long userId) {
        return borrowedBookService.getBorrowedBooksForUser(userId);
    }
}