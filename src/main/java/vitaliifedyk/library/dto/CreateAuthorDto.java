package vitaliifedyk.library.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.model.FullName;

import static vitaliifedyk.library.constant.ValidationConstants.AUTHOR_DESCRIPTION_SIZE;
import static vitaliifedyk.library.constant.ValidationConstants.NOT_NULL;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateAuthorDto {
    @Valid
    @NotNull(message = NOT_NULL)
    private FullName fullName;
    @Size(max = 300, message = AUTHOR_DESCRIPTION_SIZE)
    private String description;
}
