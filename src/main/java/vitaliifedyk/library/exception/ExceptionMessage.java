package vitaliifedyk.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ExceptionMessage {
    private String message;
    private Map<?, ?> additionalInfo;

    public ExceptionMessage(String message) {
        this.message = message;
    }

    public ExceptionMessage(final Map<?, ?> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
