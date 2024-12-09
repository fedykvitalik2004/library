package vitaliifedyk.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.model.FullName;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReadUserDto {
    private Long id;
    private FullName fullName;
    private String email;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
}
