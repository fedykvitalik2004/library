package vitaliifedyk.library.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

import static vitaliifedyk.library.constant.ValidationConstants.DATE_IN_THE_FUTURE;
import static vitaliifedyk.library.constant.ValidationConstants.NOT_NULL;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateBorrowedBookDto {
    @NotNull(message = NOT_NULL)
    private Long bookId;
    @NotNull(message = NOT_NULL)
    private Long userId;
    @Future(message = DATE_IN_THE_FUTURE)
    @NotNull(message = NOT_NULL)
    private ZonedDateTime returnDate;
}
