package vitaliifedyk.library.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vitaliifedyk.library.constant.ValidationConstants;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FullName implements Serializable {
    @NotBlank(message = ValidationConstants.BLANK)
    private String firstName;
    @NotBlank(message = ValidationConstants.BLANK)
    private String lastName;
}