package vitaliifedyk.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.model.FullName;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateAuthorDto {
    private FullName fullName;
    private String description;
}
