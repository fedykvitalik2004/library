package vitaliifedyk.library.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.enumeration.BookGenre;

import static vitaliifedyk.library.constant.ValidationConstants.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateBookDto {
    @NotNull(message = NOT_NULL)
    private String title;
    @Size(max = 500, message = BOOK_DESCRIPTION_SIZE)
    private String description;
    @NotNull(message = NOT_NULL)
    private BookGenre genre;
    @Positive(message = POSITIVE)
    @NotNull(message = NOT_NULL)
    private Short pagesCount;
    @NotNull(message = NOT_NULL)
    private Long authorId;
}
