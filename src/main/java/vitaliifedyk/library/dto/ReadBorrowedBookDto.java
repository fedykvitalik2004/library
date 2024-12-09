package vitaliifedyk.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReadBorrowedBookDto {
    private ReadBookDto book;
    private Long userId;
    private ZonedDateTime borrowDate;
    private ZonedDateTime returnDate;
}
