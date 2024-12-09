package vitaliifedyk.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.model.FullName;

import java.time.LocalDate;

import static vitaliifedyk.library.constant.ValidationConstants.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateUserDto {
    @Valid
    private FullName fullName;
    @Email(message = EMAIL_FORMAT)
    @NotNull(message = NOT_NULL)
    private String email;
    @Size(min = 8, max = 20, message = PASSWORD_SIZE)
    @NotNull(message = NOT_NULL)
    private String password;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @NotNull(message = NOT_NULL)
    @Past(message = DATE_IN_PAST)
    private LocalDate birthday;
}
