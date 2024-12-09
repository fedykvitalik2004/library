package vitaliifedyk.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.enumeration.BookGenre;
import vitaliifedyk.library.model.FullName;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReadBookDto {
    private Long id;
    private String title;
    private String description;
    private BookGenre genre;
    private short pagesCount;
    private String authorId;
    private FullName authorFullName;
}
